package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.GoodsAndOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 22:14
 */

@Mapper
public interface GoodsOrderMapper {
    @Select("select * from goods_order where goods_key=#{id}")
    List<GoodsAndOrder> findByGoods(Integer id);

    @Select("select * from goods_order where order_key=#{id}")
    List<GoodsAndOrder> findByOrder(Integer id);

    @Delete("delete from goods_order where goods_key=#{id}")
    int deleteByGoods(Integer id);

    @Delete("delete from goods_order where order_key=#{id}")
    int deleteByOrder(Integer id);

    @Insert("insert into goods_order(goods_key, order_key)" +
            "values(#{goods_key}, #{order_key})")
    int insert(GoodsAndOrder goodsAndOrder);
}
