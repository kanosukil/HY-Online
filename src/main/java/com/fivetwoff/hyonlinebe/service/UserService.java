package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.User;
import com.fivetwoff.hyonlinebe.mapper.LoginMapper;
import com.fivetwoff.hyonlinebe.mapper.UserMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.*;
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
    private LoginMapper login;
    @Autowired
    private UserCartMapper userCart;
    @Autowired
    private UserCommentMapper userComment;
    @Autowired
    private UserOrderMapper userOrder;
    @Autowired
    private UserRoleMapper userRole;
    @Autowired
    private UserStoreMapper userStore;

    public List<User> findAll() {
        return user.findAll();
    }

    public User findById(Integer id) {
        return user.findById(id);
    }

    public boolean insert(User userInsert) {
        if (user.findById(userInsert.getId()) != null) {
            log.error("id重复");
            return false;
        }
        try {
            user.insert(userInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("user插入成功");
        return true;
    }

    public boolean deleteById(Integer id) {
        int[] i = new int[6];
        int j = 0;
        try {
            j = 1;
            i[0] = userCart.deleteByUser(id);
            j = 2;
            i[1] = userComment.deleteByUser(id);
            j = 3;
            i[2] = userOrder.deleteByUser(id);
            j = 4;
            i[3] = userRole.deleteByUser(id);
            j = 5;
            i[4] = userStore.deleteByUser(id);
            j = 6;
            i[5] = user.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            if (j == 1) {
                log.error("user_cart表删除异常");
            } else if (j == 2) {
                log.error("user_comment表删除异常");
            } else if (j == 3) {
                log.error("user_order表删除异常");
            } else if (j == 4) {
                log.error("user_role表删除异常");
            } else if (j == 5) {
                log.error("user_store表删除异常");
            } else {
                log.error("user表删除异常");
            }
            return false;
        }
        log.info("user删除" + i[5] + "条信息");
        return true;
    }

    public boolean update(User userUpdate) {
        int i = 0;
        try {
            i = user.updateByPrimaryKey(userUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("user更新了" + i + "条信息");
        return true;
    }

    public User findByUsername(String username) {
        return login.queryByName(username);
    }
}
