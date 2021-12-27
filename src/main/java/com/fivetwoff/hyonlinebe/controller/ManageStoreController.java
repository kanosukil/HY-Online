package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.StatusCodeVO;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VHBin
 * @date 2021/12/27 - 10:43
 */

@Slf4j
@RestController
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

    @GetMapping("/get_all_store")
    public Map<String, List<Store>> getAllStore() {
        Map<String, List<Store>> map = new HashMap<>();
        map.put("list", store.findAll());
        return map;
    }

    // 传入数据形式: true/false
    @PostMapping("/change_status")
    public StatusCodeVO change(@RequestBody Boolean status) {
        System.out.println(status);
        if (status) {
            systemStatus.setStatus(SystemStatus.ON);
        } else {
            systemStatus.setStatus(SystemStatus.OFF);
        }
        return new StatusCodeVO(200);
    }

    @GetMapping("/get_status")
    public StatusCodeVO get() {
        System.out.println(systemStatus.getStatus());
        return new StatusCodeVO(200);
    }

    // 传入数据形式: [1, 2, 3] 数组(不要带名称)
    @PostMapping("/close_store")
    public StatusCodeVO closeStore(@RequestBody Integer[] storeIdList, HttpServletResponse response) {
        try {
            for (var storeId : storeIdList) {
                List<StoreAndGoods> sgs = storeGoods.findByStore(storeId);
                for (StoreAndGoods sg : sgs) {
                    if (!goods.deleteById(sg.getGoods_key())) {
                        throw new Exception("goods表删除失败");
                    }
                }
                if (!store.deleteById(storeId)) {
                    throw new Exception("store删除失败");
                }
            }
        } catch (Exception ex) {
            log.error(ex.toString());
            response.setStatus(500);
            return new StatusCodeVO(500);
        }
        response.setStatus(200);
        return new StatusCodeVO(200);
    }

    // 传入数据形式: number
    @PostMapping("/is_open_store")
    public StoreVO isOpen(@RequestBody Integer userId) {
        try {
            if (user.findById(userId) == null) {
                log.error("用户未知");
                return new StoreVO(403, false);
            }
            List<UserAndStore> lists = userStore.findByUser(userId);
            if (lists.size() != 0) {
                return new StoreVO(200, true);
            } else {
                return new StoreVO(200, false);
            }
        } catch (Exception ex) {
            log.error(ex.toString());
            return new StoreVO(500, false);
        }
    }

    // 传入数据形式: {"userName": "str", "userKey": number}
    @PostMapping("/create_store")
    public StatusCodeVO create(@RequestBody StoreDTO storeDTO, HttpServletResponse response) {
        try {
            if (user.findById(storeDTO.getUserKey()) == null) {
                log.error("用户未知");
                response.setStatus(403);
                return new StatusCodeVO(403);
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
                    if (!userStore.insert(us)) {
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
            return new StatusCodeVO(500);
        }
        response.setStatus(200);
        return new StatusCodeVO(200);
    }
}
