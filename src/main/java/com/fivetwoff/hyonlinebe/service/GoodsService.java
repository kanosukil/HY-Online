package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.mapper.GoodsMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.GoodsCartMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.StoreGoodsMapper;
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
    private GoodsCartMapper goodsCart;
    @Autowired
    private StoreGoodsMapper storeGoods;

    public List<Goods> findAll() {
        return goods.findAll();
    }

    public Goods findById(Integer id) {
        return goods.findById(id);
    }

    public boolean insert(Goods goodsInsert) {
        if (goods.findById(goodsInsert.getId()) != null) {
            log.error("id重复");
            return false;
        }
        try {
            goods.insert(goodsInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        int[] i = new int[3];
        int j = 0;
        try {
            j = 1;
            i[0] = goodsCart.deleteByGoods(id);
            j = 2;
            i[1] = storeGoods.deleteByGoods(id);
            j = 3;
            i[2] = goods.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            if (j == 1) {
                log.error("goods_cart表删除异常");
            } else if (j == 2) {
                log.error("store_goods表删除异常");
            } else {
                log.error("goods表删除异常");
            }
            return false;
        }
        log.info("删除" + i[2] + "条信息");
        return true;
    }

    public boolean update(Goods goodsUpdate) {
        int i = 0;
        try {
            i = goods.updateByPrimaryKey(goodsUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("更新了" + i + "条信息");
        return true;
    }
}
