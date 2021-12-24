package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.mapper.GoodsMapper;
import com.fivetwoff.hyonlinebe.service.cascade.GoodsCartService;
import com.fivetwoff.hyonlinebe.service.cascade.StoreGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/23 - 21:26
 */

@Slf4j
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goods;
    @Autowired
    private GoodsCartService goodsCart;
    @Autowired
    private StoreGoodsService storeGoods;

    public List<Goods> findAll() {
        return goods.findAll();
    }

    public Goods findById(Integer id) {
        return goods.findById(id);
    }

    public boolean insert(Goods goodsInsert) {
        try {
            goods.insert(goodsInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        if (goodsCart.deleteByGoods(id) && storeGoods.deleteByGoods(id)) {
            try {
                goods.deleteById(id);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        } else {
            log.error("goods_cart表或store_goods表删除goods主键异常");
            return false;
        }
        return true;
    }

    public boolean update(Goods goodsUpdate) {
        try {
            goods.updateByPrimaryKey(goodsUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }
}
