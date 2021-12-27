package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.StoreAndGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 22:07
 */

@Mapper
public interface StoreGoodsMapper {
    @Select("select * from store_goods where store_key=#{id}")
    List<StoreAndGoods> findByStore(Integer id);

    @Select("select * from store_goods where goods_key=#{id}")
    List<StoreAndGoods> findByGoods(Integer id);

    @Delete("delete from store_goods where store_key=#{id}")
    int deleteByStore(Integer id);

    @Delete("delete from store_goods where goods_key=#{id}")
    int deleteByGoods(Integer id);

    @Delete("delete from store_goods where goods_key=#{gid} and store_key=#{sid}")
    int deleteByStoreAndGoods(Integer gid, Integer sid);

    @Insert("insert into store_goods(store_key, goods_key)" +
            "values(#{store_key}, #{goods_key})")
    int insert(StoreAndGoods storeAndGoods);
}
