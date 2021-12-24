package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndCart;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserCartMapper;
import com.fivetwoff.hyonlinebe.service.CartService;
import com.fivetwoff.hyonlinebe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UserService user;
    @Autowired
    private CartService cart;

    public List<UserAndCart> findByUser(Integer id) {
        return userCart.findByUser(id);
    }

    public List<UserAndCart> findByCart(Integer id) {
        return userCart.findByCart(id);
    }

    public boolean deleteByUser(Integer id) {
        try {
            userCart.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteByCart(Integer id) {
        try {
            userCart.deleteByCart(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
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
                userCart.insert(userAndCart);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
