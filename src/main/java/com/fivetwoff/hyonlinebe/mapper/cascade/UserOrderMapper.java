package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 22:32
 */

@Mapper
public interface UserOrderMapper {
    @Select("select * form user_order where user_key=#{id}")
    List<UserAndOrder> findByUser(Integer id);

    @Select("select * form user_order where order_key=#{id}")
    List<UserAndOrder> findByOrder(Integer id);

    @Delete("delete from user_order where user_key=#{id}")
    int deleteByUser(Integer id);

    @Delete("delete from user_order where order_key=#{id}")
    int deleteByOrder(Integer id);

    @Insert("insert into user_order(user_key, order_key)" +
            "values(#{user_key}, #{order_key})")
    int insert(UserAndOrder userAndOrder);
}
