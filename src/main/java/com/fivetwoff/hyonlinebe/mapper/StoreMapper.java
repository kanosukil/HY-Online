package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.Store;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:42
 */

@Mapper
public interface StoreMapper {
    @Select("select * from store")
    List<Store> findAll();

    @Select("select * from store where id=#{id}")
    Store findById(Integer id);

    @Delete("delete from store where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into store(id, name)" +
            "values(#{id}, #{name})")
    int insert(Store store);

    @Update("update store" +
            "       set name=#{name}" +
            "       where id=#{id}")
    int updateByPrimaryKey(Store store);
}
