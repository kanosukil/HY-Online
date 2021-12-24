package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Store;
import com.fivetwoff.hyonlinebe.mapper.StoreMapper;
import com.fivetwoff.hyonlinebe.service.cascade.StoreGoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.StoreOrderService;
import com.fivetwoff.hyonlinebe.service.cascade.UserStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/23 - 21:33
 */

@Slf4j
@Service
public class StoreService {
    @Autowired
    private StoreMapper store;
    @Autowired
    private StoreGoodsService storeGoods;
    @Autowired
    private StoreOrderService storeOrder;
    @Autowired
    private UserStoreService userStore;

    public List<Store> findAll() {
        return store.findAll();
    }

    public Store findById(Integer id) {
        return store.findById(id);
    }

    public boolean insert(Store storeInsert) {
        try {
            store.insert(storeInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        if (userStore.deleteByStore(id) && storeGoods.deleteByStore(id) && storeOrder.deleteByStore(id)) {
            try {
                store.deleteById(id);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        } else {
            log.error("user_store表或store_order表或store_goods表删除store主键异常");
            return false;
        }
        return true;
    }

    public boolean update(Store storeUpdate) {
        try {
            store.updateByPrimaryKey(storeUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }
}
