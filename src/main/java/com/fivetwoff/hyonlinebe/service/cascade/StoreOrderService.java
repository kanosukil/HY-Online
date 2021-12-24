package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.StoreAndOrder;
import com.fivetwoff.hyonlinebe.mapper.OrdersMapper;
import com.fivetwoff.hyonlinebe.mapper.StoreMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.StoreOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:13
 */

@Slf4j
@Service
public class StoreOrderService {
    @Autowired
    private StoreOrderMapper storeOrder;
    @Autowired
    private StoreMapper store;
    @Autowired
    private OrdersMapper order;

    public List<StoreAndOrder> findByStore(Integer id) {
        return storeOrder.findByStore(id);
    }

    public List<StoreAndOrder> findByOrder(Integer id) {
        return storeOrder.findByOrder(id);
    }

    public boolean deleteByStore(Integer id) {
        int i = 0;
        try {
            i = storeOrder.deleteByStore(id);
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
            i = storeOrder.deleteByOrder(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean insert(StoreAndOrder storeAndOrder) {
        Integer storeKey = storeAndOrder.getStore_key();
        Integer orderKey = storeAndOrder.getOrder_key();
        if (store.findById(storeKey) == null || order.findById(orderKey) == null) {
            log.error("store表和order表内没有对应主键");
            return false;
        } else {
            try {
                storeOrder.insert(storeAndOrder);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
