package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Store;
import com.fivetwoff.hyonlinebe.mapper.StoreMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.StoreGoodsMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserStoreMapper;
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
    private StoreGoodsMapper storeGoods;
    @Autowired
    private UserStoreMapper userStore;

    public List<Store> findAll() {
        return store.findAll();
    }

    public Store findById(Integer id) {
        return store.findById(id);
    }

    public boolean insert(Store storeInsert) {
        if (store.findById(storeInsert.getId()) != null) {
            log.error("id重复");
            return false;
        }
        try {
            store.insert(storeInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        int[] i = new int[4];
        int j = 0;
        try {
            j = 1;
            i[0] = storeGoods.deleteByStore(id);
            j = 3;
            i[2] = userStore.deleteByStore(id);
            j = 4;
            i[3] = store.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            if (j == 1) {
                log.error("store_goods表删除异常");
            } else if (j == 3) {
                log.error("user_store表删除异常");
            } else {
                log.error("store表删除异常");
            }
            return false;
        }
        log.info("删除" + i[3] + "条信息");
        return true;
    }

    public boolean update(Store storeUpdate) {
        int i = 0;
        try {
            i = store.updateByPrimaryKey(storeUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("更新了" + i + "条信息");
        return true;
    }
}
