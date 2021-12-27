package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:28
 */

@Mapper
public interface GoodsMapper {
    @Select("Select * from goods")
    List<Goods> findAll();

    @Select("select * from goods where id=#{id}")
    Goods findById(Integer id);

    @Delete("delete from goods where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into goods(id, name, img, price, description, number)" +
            "values(#{id}, #{name}, #{img}, #{price}, #{description}, #{number})")
    int insert(Goods goods);

    @Update("update goods" +
            "       set name=#{name}," +
            "           img=#{img}," +
            "           price=#{price}," +
            "           description=#{description}," +
            "           number=#{number}" +
            "       where id=#{id}")
    int updateByPrimaryKey(Goods goods);
}
