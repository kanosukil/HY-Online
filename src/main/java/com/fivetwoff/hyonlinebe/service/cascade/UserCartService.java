package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.entity.cascade.UserAndCart;
import com.fivetwoff.hyonlinebe.mapper.CartMapper;
import com.fivetwoff.hyonlinebe.mapper.UserMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:25
 */

@Slf4j
@Service
public class UserCartService {
    @Autowired
    private UserCartMapper userCart;
    @Autowired
    private UserMapper user;
    @Autowired
    private CartMapper cart;

    public UserAndCart findByUser(Integer id) {
        return userCart.findByUser(id);
    }

    public UserAndCart findByCart(Integer id) {
        return userCart.findByCart(id);
    }

    public boolean deleteByUser(Integer id) {
        int i = 0;
        try {
            i = userCart.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("user_cart删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByCart(Integer id) {
        int i = 0;
        try {
            i = userCart.deleteByCart(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("user_cart删除了" + i + "条信息");
        return true;
    }

    public boolean insert(UserAndCart userAndCart) {
        Integer userKey = userAndCart.getUser_key();
        Integer cartKey = userAndCart.getCart_key();
        if (user.findById(userKey) == null || cart.findById(cartKey) == null) {
            log.error("user表和cart表内没有对应主键");
            return false;
        } else {
            try {
                if (userCart.findByUser(userKey) != null) {
                    log.error("user对象已存在");
                    return false;
                } else if (userCart.findByCart(cartKey) != null) {
                    log.error("cart对象已存在");
                    return false;
                } else {
                    userCart.insert(userAndCart);
                }
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        log.info("user_cart插入成功");
        return true;
    }
}
