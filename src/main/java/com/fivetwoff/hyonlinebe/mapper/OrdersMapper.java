package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:36
 */

@Mapper
public interface OrdersMapper {
    @Select("select * from orders")
    List<Orders> findAll();

    @Select("select * from orders where id=#{id}")
    Orders findById(Integer id);

    @Delete("delete from orders where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into orders(id, number, address)" +
            "values(#{id}, #{number}, #{address})")
    int insert(Orders order);

    @Update("update orders" +
            "       set number=#{number}," +
            "           address=#{address}" +
            "       where id=#{id}")
    int updateByPrimaryKey(Orders order);
}
