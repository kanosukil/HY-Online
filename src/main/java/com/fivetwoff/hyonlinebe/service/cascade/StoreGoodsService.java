package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.StoreAndGoods;
import com.fivetwoff.hyonlinebe.mapper.cascade.StoreGoodsMapper;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.StoreService;
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
    private StoreService store;
    @Autowired
    private GoodsService goods;

    public List<StoreAndGoods> findByStore(Integer id) {
        return storeGoods.findByStore(id);
    }

    public List<StoreAndGoods> findByGoods(Integer id) {
        return storeGoods.findByGoods(id);
    }

    public boolean deleteByStore(Integer id) {
        try {
            storeGoods.deleteByStore(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteByGoods(Integer id) {
        try {
            storeGoods.deleteByGoods(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
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
