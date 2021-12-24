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

    @Insert("insert into cart(id, totalprice) " +
            "values(#{id}, #{totalprice})")
    int insert(Cart cart);

    @Update("update cart" +
            "       set totalprice=#{totalprice}" +
            "       where id=#{id}")
    int updateByPrimaryKey(Cart cart);
}
