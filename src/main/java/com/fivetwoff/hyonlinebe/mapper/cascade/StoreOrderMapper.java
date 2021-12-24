package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.StoreAndOrder;
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
public interface StoreOrderMapper {
    @Select("select * from store_order where store_key=#{id}")
    List<StoreAndOrder> findByStore(Integer id);

    @Select("select * from store_order where order_key=#{id}")
    List<StoreAndOrder> findByOrder(Integer id);

    @Delete("delete from store_order where store_key=#{id}")
    int deleteByStore(Integer id);

    @Delete("delete from store_order where order_key=#{id}")
    int deleteByOrder(Integer id);

    @Insert("insert into store_order(store_key, order_key)" +
            "values(#{store_key}, #{order_key})")
    int insert(StoreAndOrder storeAndOrder);
}
