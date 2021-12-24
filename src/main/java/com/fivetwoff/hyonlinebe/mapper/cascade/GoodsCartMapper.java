package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.GoodsAndCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:54
 */

@Mapper
public interface GoodsCartMapper {
    @Select("select * from goods_cart where goods_key=#{id}")
    List<GoodsAndCart> findByGoods(Integer id);

    @Select("select * from goods_cart where cart_key=#{id}")
    List<GoodsAndCart> findByCart(Integer id);

    @Delete("delete from goods_cart where goods_key=#{id}")
    int deleteByGoods(Integer id);

    @Delete("delete from goods_cart where cart_key=#{id}")
    int deleteByCart(Integer id);

    @Insert("insert into goods_cart(goods_key, cart_key)" +
            "values(#{goods_key}, #{cart_key})")
    int insert(GoodsAndCart goodsAndCart);
}
