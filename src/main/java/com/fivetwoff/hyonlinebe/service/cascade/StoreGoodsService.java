package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.entity.cascade.StoreAndGoods;
import com.fivetwoff.hyonlinebe.mapper.GoodsMapper;
import com.fivetwoff.hyonlinebe.mapper.StoreMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.StoreGoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:04
 */

@Slf4j
@Service
public class StoreGoodsService {
    @Autowired
    private StoreGoodsMapper storeGoods;
    @Autowired
    private StoreMapper store;
    @Autowired
    private GoodsMapper goods;

    public List<StoreAndGoods> findByStore(Integer id) {
        return storeGoods.findByStore(id);
    }

    public List<StoreAndGoods> findByGoods(Integer id) {
        return storeGoods.findByGoods(id);
    }

    public boolean deleteByStore(Integer id) {
        int i = 0;
        try {
            i = storeGoods.deleteByStore(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByGoods(Integer id) {
        int i = 0;
        try {
            i = storeGoods.deleteByGoods(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByGoodsAndStore(Integer gid, Integer sid) {
        int i = 0;
        try {
            i = storeGoods.deleteByStoreAndGoods(gid, sid);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean insert(StoreAndGoods storeAndGoods) {
        Integer storeKey = storeAndGoods.getStore_key();
        Integer goodsKey = storeAndGoods.getGoods_key();
        if (store.findById(storeKey) == null || goods.findById(goodsKey) == null) {
            log.error("store表和goods表内没有对应主键");
            return false;
        } else {
            try {
                storeGoods.insert(storeAndGoods);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
