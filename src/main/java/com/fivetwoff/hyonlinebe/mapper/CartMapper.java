package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:47
 */

@Mapper
public interface CartMapper {
    @Select("select * from cart")
    List<Cart> findAll();

    @Select("select * from cart where id=#{id}")
    Cart findById(Integer id);

    @Delete("delete from cart where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into cart(id, total_price) " +
            "values(#{id}, #{total_price})")
    int insert(Cart cart);

    @Update("update cart" +
            "       set total_price=#{total_price}" +
            "       where id=#{id}")
    int updateByPrimaryKey(Cart cart);
}
