package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:26
 */

@Mapper
public interface UserCartMapper {
    @Select("select * form user_cart where user_key=#{id}")
    List<UserAndCart> findByUser(Integer id);

    @Select("select * form user_cart where cart_key=#{id}")
    List<UserAndCart> findByCart(Integer id);

    @Delete("delete from user_cart where user_key=#{id}")
    int deleteByUser(Integer id);

    @Delete("delete from user_cart where cart_key=#{id}")
    int deleteByCart(Integer id);

    @Insert("insert into user_cart(user_key, cart_key)" +
            "values(#{user_key}, #{cart_key})")
    int insert(UserAndCart userAndCart);
}
