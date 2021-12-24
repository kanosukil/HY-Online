package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Cart;
import com.fivetwoff.hyonlinebe.mapper.CartMapper;
import com.fivetwoff.hyonlinebe.service.cascade.GoodsCartService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCartService;
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
    private GoodsCartService goodsCart;
    @Autowired
    private UserCartService userCart;

    public List<Cart> findAll() {
        return cart.findAll();
    }

    public Cart findById(Integer id) {
        return cart.findById(id);
    }

    public boolean insert(Cart cartInsert) {
        try {
            cart.insert(cartInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        if (goodsCart.deleteByCart(id) && userCart.deleteByCart(id)) {
            try {
                cart.deleteById(id);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        } else {
            log.error("goods_cart表或user_cart表删除cart主键异常");
            return false;
        }
        return true;
    }

    public boolean update(Cart cartUpdate) {
        try {
            cart.updateByPrimaryKey(cartUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }
}
