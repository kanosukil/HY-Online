package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.GoodsAndOrder;
import com.fivetwoff.hyonlinebe.mapper.GoodsMapper;
import com.fivetwoff.hyonlinebe.mapper.OrdersMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.GoodsOrderMapper;
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
public class GoodsOrderService {
    @Autowired
    private GoodsOrderMapper goodsOrder;
    @Autowired
    private GoodsMapper goods;
    @Autowired
    private OrdersMapper order;

    public List<GoodsAndOrder> findByStore(Integer id) {
        return goodsOrder.findByGoods(id);
    }

    public List<GoodsAndOrder> findByOrder(Integer id) {
        return goodsOrder.findByOrder(id);
    }

    public boolean deleteByGoods(Integer id) {
        int i = 0;
        try {
            i = goodsOrder.deleteByGoods(id);
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
            i = goodsOrder.deleteByOrder(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean insert(GoodsAndOrder goodsAndOrder) {
        Integer storeKey = goodsAndOrder.getGoods_key();
        Integer orderKey = goodsAndOrder.getOrder_key();
        if (goods.findById(storeKey) == null || order.findById(orderKey) == null) {
            log.error("store表和order表内没有对应主键");
            return false;
        } else {
            try {
                goodsOrder.insert(goodsAndOrder);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
