package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.StoreDTO;
import com.fivetwoff.hyonlinebe.DTO.StoreIdListDTO;
import com.fivetwoff.hyonlinebe.DTO.SystemStatusDTO;
import com.fivetwoff.hyonlinebe.DTO.UserIdDTO;
import com.fivetwoff.hyonlinebe.VO.AllStoreVO;
import com.fivetwoff.hyonlinebe.VO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.VO.StatusVO;
import com.fivetwoff.hyonlinebe.VO.StoreVO;
import com.fivetwoff.hyonlinebe.config.SystemStatus;
import com.fivetwoff.hyonlinebe.entity.Store;
import com.fivetwoff.hyonlinebe.entity.cascade.StoreAndGoods;
import com.fivetwoff.hyonlinebe.entity.cascade.UserAndRole;
import com.fivetwoff.hyonlinebe.entity.cascade.UserAndStore;
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
import java.util.ArrayList;
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
    public Map<String, Object> getAllStore() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("info", "Normal Server");
        List<Store> stores = store.findAll();
        List<AllStoreVO> allStoreVOS = new ArrayList<>();
        try {
            for (Store store : stores) {
                allStoreVOS.add(new AllStoreVO(store.getId(), store.getName()));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            map.put("code", 500);
            map.put("info", e.toString());
            allStoreVOS = null;
        }
        map.put("list", allStoreVOS);
        return map;
    }

    // 传入数据形式: true/false
    @PostMapping("/change_status")
    public StatusCodeVO change(@RequestBody SystemStatusDTO status, HttpServletResponse response) {
        if (status == null || status.getStatus() == null) {
            response.setStatus(404);
            return new StatusCodeVO(404, "传入数据有null");
        }
        System.out.println(status);
        if (status.getStatus()) {
            systemStatus.setStatus(SystemStatus.ON);
        } else {
            systemStatus.setStatus(SystemStatus.OFF);
        }
        response.setStatus(200);

        return new StatusCodeVO(200, "Normal Server");
    }

    @GetMapping("/get_status")
    public StatusVO get() {
        System.out.println(systemStatus.getStatus());
        return new StatusVO(200, systemStatus.getStatus());
    }

    // 传入数据形式: {"storeIds": [1, 2, 3]} 数组
    @PostMapping("/close_store")
    public StatusCodeVO closeStore(@RequestBody StoreIdListDTO list, HttpServletResponse response) {
        if (list == null || list.getStoreIds() == null) {
            response.setStatus(404);
            return new StatusCodeVO(404, "传入数据有null");
        }
        try {
            for (var storeId : list.getStoreIds()) {
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
            return new StatusCodeVO(500, ex.toString());
        }
        response.setStatus(200);
        return new StatusCodeVO(200, "Normal Server");
    }

    // 传入数据形式: {"userId": number}
    @PostMapping("/is_open_store")
    public StoreVO isOpen(@RequestBody UserIdDTO userId, HttpServletResponse response) {
        if (userId == null || userId.getUserId() == null) {
            response.setStatus(404);
            return new StoreVO(404, false, "传入数据有null");
        }
        try {
            if (user.findById(userId.getUserId()) == null) {
                log.error("用户未知");
                response.setStatus(403);
                return new StoreVO(403, false, "用户未知");
            }
            List<UserAndStore> lists = userStore.findByUser(userId.getUserId());
            response.setStatus(200);
            if (lists.size() != 0) {
                return new StoreVO(200, true, "Normal Server");
            } else {
                return new StoreVO(200, false, "Normal Server");
            }
        } catch (Exception ex) {
            log.error(ex.toString());
            response.setStatus(500);
            return new StoreVO(500, false, ex.toString());
        }
    }

    // 传入数据形式: {"storeName": "str", "userKey": number}
    @PostMapping("/create_store")
    public StatusCodeVO create(@RequestBody StoreDTO storeDTO, HttpServletResponse response) {
        if (storeDTO == null || storeDTO.getUserKey() == null || storeDTO.getStoreName() == null) {
            response.setStatus(404);
            return new StatusCodeVO(404, "传入数据有null");
        }
        try {
            if (user.findById(storeDTO.getUserKey()) == null) {
                log.error("用户未知");
                response.setStatus(403);
                return new StatusCodeVO(403, "用户未知");
            }
            UserAndRole ur = new UserAndRole();
            ur.setRole_key(2);
            ur.setUser_key(storeDTO.getUserKey());
            if (userRole.insert(ur)) {
                Store st = new Store();
                st.setId(store.findAll().get(store.findAll().size() - 1).getId() + 1);
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
            return new StatusCodeVO(500, ex.toString());
        }
        response.setStatus(200);
        return new StatusCodeVO(200, "Normal Server");
    }
}
