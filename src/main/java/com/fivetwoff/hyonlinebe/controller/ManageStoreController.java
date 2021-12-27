package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.StoreDTO;
import com.fivetwoff.hyonlinebe.DTO.StoreVO;
import com.fivetwoff.hyonlinebe.cascade.StoreAndGoods;
import com.fivetwoff.hyonlinebe.cascade.UserAndRole;
import com.fivetwoff.hyonlinebe.cascade.UserAndStore;
import com.fivetwoff.hyonlinebe.config.SystemStatus;
import com.fivetwoff.hyonlinebe.entity.Store;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.StoreService;
import com.fivetwoff.hyonlinebe.service.UserService;
import com.fivetwoff.hyonlinebe.service.cascade.StoreGoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.UserRoleService;
import com.fivetwoff.hyonlinebe.service.cascade.UserStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VHBin
 * @date 2021/12/27 - 10:43
 */

@Slf4j
@Controller
@RequestMapping("/manage")
public class ManageStoreController {
    @Autowired
    private StoreService store;
    @Autowired
    private UserStoreService userStore;
    @Autowired
    private UserService user;
    @Autowired
    private UserRoleService userRole;
    @Autowired
    private GoodsService goods;
    @Autowired
    private StoreGoodsService storeGoods;
    @Autowired
    private SystemStatus systemStatus;

    @RequestMapping("/get_all_store")
    public Map<String, List<Store>> getAllStore() {
        Map<String, List<Store>> map = new HashMap<>();
        map.put("list", store.findAll());
        return map;
    }

    @RequestMapping("/change_status")
    public void change(@RequestBody Boolean status, HttpServletResponse response) {
        systemStatus.setStatus(status);
        response.setStatus(200);
    }


    @RequestMapping("/close_store")
    public void closeStore(@RequestBody List<String> storeIdList, HttpServletResponse response) {
        try {
            for (String storeId : storeIdList) {
                List<StoreAndGoods> sgs = storeGoods.findByStore(Integer.parseInt(storeId));
                for (StoreAndGoods sg : sgs) {
                    if (!goods.deleteById(sg.getGoods_key())) {
                        throw new Exception("goods表删除失败");
                    }
                }
                if (store.deleteById(Integer.parseInt(storeId))) {
                    response.setStatus(200);
                } else {
                    throw new Exception("store删除失败");
                }
            }
        } catch (Exception ex) {
            log.error(ex.toString());
            response.setStatus(500);
        }

    }

    @RequestMapping("/is_open_store")
    public StoreVO isOpen(@RequestBody String userId) {
        try {
            if (user.findById(Integer.parseInt(userId)) == null) {
                log.error("用户未知");
                return new StoreVO(403, false);
            }
            List<UserAndStore> lists = userStore.findByUser(Integer.parseInt(userId));
            if (lists.size() == 0) {
                return new StoreVO(200, true);
            } else {
                return new StoreVO(200, false);
            }
        } catch (Exception ex) {
            log.error(ex.toString());
            return new StoreVO(500, false);
        }
    }

    @RequestMapping("/create_store")
    public void create(@RequestBody StoreDTO storeDTO, HttpServletResponse response) {
        try {
            if (user.findById(storeDTO.getUserKey()) == null) {
                log.error("用户未知");
                response.setStatus(403);
            }
            UserAndRole ur = new UserAndRole();
            ur.setRole_key(2);
            ur.setUser_key(storeDTO.getUserKey());
            if (userRole.insert(ur)) {
                Store st = new Store();
                st.setId(store.findAll().size() + 1);
                st.setName(storeDTO.getStoreName());
                if (store.insert(st)) {
                    UserAndStore us = new UserAndStore();
                    us.setMaster_key(storeDTO.getUserKey());
                    us.setStore_key(st.getId());
                    if (userStore.insert(us)) {
                        response.setStatus(200);
                    } else {
                        throw new Exception("user_store表插入失败");
                    }
                } else {
                    throw new Exception("store表插入失败");
                }
            } else {
                throw new Exception("user_role表插入失败");
            }
        } catch (Exception ex) {
            response.setStatus(500);
        }
    }
}
