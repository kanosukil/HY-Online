package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.User;
import com.fivetwoff.hyonlinebe.mapper.UserMapper;
import com.fivetwoff.hyonlinebe.service.cascade.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/23 - 21:34
 */

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper user;
    @Autowired
    private UserCartService userCart;
    @Autowired
    private UserCommentService userComment;
    @Autowired
    private UserOrderService userOrder;
    @Autowired
    private UserRoleService userRole;
    @Autowired
    private UserStoreService userStore;

    public List<User> findAll() {
        return user.findAll();
    }

    public User findById(Integer id) {
        return user.findById(id);
    }

    public boolean insert(User userInsert) {
        try {
            user.insert(userInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        if (userCart.deleteByUser(id) && userComment.deleteByUser(id) && userOrder.deleteByUser(id) &&
                userRole.deleteByUser(id) && userStore.deleteByUser(id)) {
            try {
                user.deleteById(id);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        } else {
            log.error("user_cart表或user_comment表或user_order表或user_role表或user_store表删除user主键异常");
            return false;
        }
        return true;
    }

    public boolean update(User userUpdate) {
        try {
            user.updateByPrimaryKey(userUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }
}
