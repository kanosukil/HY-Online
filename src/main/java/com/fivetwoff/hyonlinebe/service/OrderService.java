package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Order;
import com.fivetwoff.hyonlinebe.mapper.OrderMapper;
import com.fivetwoff.hyonlinebe.service.cascade.StoreOrderService;
import com.fivetwoff.hyonlinebe.service.cascade.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/23 - 21:29
 */

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper order;
    @Autowired
    private UserOrderService userOrder;
    @Autowired
    private StoreOrderService storeOrder;

    public List<Order> findAll() {
        return order.findAll();
    }

    public Order findById(Integer id) {
        return order.findById(id);
    }

    public boolean insert(Order orderInsert) {
        try {
            order.insert(orderInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        if (userOrder.deleteByOrder(id) && storeOrder.deleteByOrder(id)) {
            try {
                order.deleteById(id);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        } else {
            log.error("user_order表或store_order表删除order主键异常");
            return false;
        }
        return true;
    }

    public boolean update(Order orderUpdate) {
        try {
            order.updateByPrimaryKey(orderUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }
}
