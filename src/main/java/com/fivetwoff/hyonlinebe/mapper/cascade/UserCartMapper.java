package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.entity.cascade.UserAndCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:26
 */

@Mapper
public interface UserCartMapper {
    @Select("select * from user_cart where user_key=#{id}")
    UserAndCart findByUser(Integer id);

    @Select("select * from user_cart where cart_key=#{id}")
    UserAndCart findByCart(Integer id);

    @Delete("delete from user_cart where user_key=#{id}")
    int deleteByUser(Integer id);

    @Delete("delete from user_cart where cart_key=#{id}")
    int deleteByCart(Integer id);

    @Insert("insert into user_cart(user_key, cart_key)" +
            "values(#{user_key}, #{cart_key})")
    int insert(UserAndCart userAndCart);
}
