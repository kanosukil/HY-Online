package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndOrder;
import com.fivetwoff.hyonlinebe.mapper.OrdersMapper;
import com.fivetwoff.hyonlinebe.mapper.UserMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:37
 */

@Slf4j
@Service
public class UserOrderService {
    @Autowired
    private UserOrderMapper userOrder;
    @Autowired
    private UserMapper user;
    @Autowired
    private OrdersMapper order;

    public List<UserAndOrder> findByUser(Integer id) {
        return userOrder.findByUser(id);
    }

    public List<UserAndOrder> findByOrder(Integer id) {
        return userOrder.findByOrder(id);
    }

    public boolean deleteByUser(Integer id) {
        int i = 0;
        try {
            i = userOrder.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByOrder(Integer id) {
        int i = 0;
        try {
            i = userOrder.deleteByOrder(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean insert(UserAndOrder userAndOrder) {
        Integer userKey = userAndOrder.getCustomer_key();
        Integer orderKey = userAndOrder.getOrder_key();
        if (user.findById(userKey) == null || order.findById(orderKey) == null) {
            log.error("user表和order表内没有对应主键");
            return false;
        } else {
            try {
                userOrder.insert(userAndOrder);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
