package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.entity.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.mapper.CartMapper;
import com.fivetwoff.hyonlinebe.mapper.GoodsMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.GoodsCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 10:51
 */

@Slf4j
@Service
public class GoodsCartService {
    @Autowired
    private GoodsCartMapper goodsCart;
    @Autowired
    private GoodsMapper goods;
    @Autowired
    private CartMapper cart;

    public List<GoodsAndCart> findByGoods(Integer id) {
        return goodsCart.findByGoods(id);
    }

    public List<GoodsAndCart> findByCart(Integer id) {
        return goodsCart.findByCart(id);
    }

    public boolean deleteByGoods(Integer id) {
        int i = 0;
        try {
            i = goodsCart.deleteByGoods(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("goods_cart删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByCart(Integer id) {
        int i = 0;
        try {
            i = goodsCart.deleteByCart(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("goods_cart删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByGoodsAndCart(Integer gid, Integer cid) {
        int i = 0;
        try {
            i = goodsCart.deleteByGoodsAndCart(gid, cid);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("goods_cart删除了" + i + "条信息");
        return true;
    }

    public boolean insert(GoodsAndCart goodsAndCart) {
        Integer goodsKey = goodsAndCart.getGoods_key();
        Integer cartKey = goodsAndCart.getCart_key();
        if (goods.findById(goodsKey) == null || cart.findById(cartKey) == null) {
            log.error("goods和cart表内没有对应主键");
            return false;
        } else {
            try {
                goodsCart.insert(goodsAndCart);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        log.info("goods_cart插入成功");
        return true;
    }
}
