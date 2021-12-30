package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Cart;
import com.fivetwoff.hyonlinebe.mapper.CartMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.GoodsCartMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 22:53
 */

@Slf4j
@Service
public class CartService {
    @Autowired
    private CartMapper cart;
    @Autowired
    private GoodsCartMapper goodsCart;
    @Autowired
    private UserCartMapper userCart;

    public List<Cart> findAll() {
        return cart.findAll();
    }

    public Cart findById(Integer id) {
        return cart.findById(id);
    }

    public boolean insert(Cart cartInsert) {
        if (cart.findById(cartInsert.getId()) != null) {
            log.error("id重复");
            return false;
        }
        try {
            cart.insert(cartInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("cart插入成功");
        return true;
    }

    public boolean deleteById(Integer id) {
        int[] i = new int[3];
        int j = 0;
        try {
            j = 1;
            i[0] = goodsCart.deleteByCart(id);
            j = 2;
            i[1] = userCart.deleteByCart(id);
            j = 3;
            i[2] = cart.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            if (j == 1) {
                log.error("good_cart表删除异常");
            } else if (j == 2) {
                log.error("user_cart表删除异常");
            } else {
                log.error("cart表删除异常");
            }
            return false;
        }
        log.info("cart删除" + i[2] + "条信息");
        return true;
    }

    public boolean update(Cart cartUpdate) {
        int i = 0;
        try {
            i = cart.updateByPrimaryKey(cartUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("cart更新了" + i + "条信息");
        return true;
    }
}
