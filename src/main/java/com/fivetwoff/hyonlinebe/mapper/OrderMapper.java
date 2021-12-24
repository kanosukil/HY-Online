package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:36
 */

@Mapper
public interface OrderMapper {
    @Select("select * from order")
    List<Order> findAll();

    @Select("select * from order where id=#{id}")
    Order findById(Integer id);

    @Delete("delete from order where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into order(id, number, address)" +
            "values(#{id}, #{number}, #{address})")
    int insert(Order order);

    @Update("update comment" +
            "       set number=#{number}" +
            "           address=#{address}" +
            "       where id=#{id}")
    int updateByPrimaryKey(Order order);
}
