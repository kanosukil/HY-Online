package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Orders;
import com.fivetwoff.hyonlinebe.mapper.OrdersMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.GoodsOrderMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserOrderMapper;
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
    private OrdersMapper order;
    @Autowired
    private UserOrderMapper userOrder;
    @Autowired
    private GoodsOrderMapper goodsOrder;

    public List<Orders> findAll() {
        return order.findAll();
    }

    public Orders findById(Integer id) {
        return order.findById(id);
    }

    public boolean insert(Orders orderInsert) {
        if (order.findById(orderInsert.getId()) != null) {
            log.error("id重复");
            return false;
        }
        try {
            order.insert(orderInsert);
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
            i[0] = userOrder.deleteByOrder(id);
            j = 2;
            i[1] = goodsOrder.deleteByOrder(id);
            j = 3;
            i[2] = order.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            if (j == 1) {
                log.error("user_order表删除异常");
            } else if (j == 2) {
                log.error("store_order表删除异常");
            } else {
                log.error("order表删除异常");
            }
            return false;
        }
        log.info("删除" + i[2] + "条信息");
        return true;
    }

    public boolean update(Orders orderUpdate) {
        int i = 0;
        try {
            i = order.updateByPrimaryKey(orderUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("更新了" + i + "条信息");
        return true;
    }
}
