package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.GoodsAndComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/27 - 09:29
 */

@Mapper
public interface GoodsCommentMapper {
    @Select("select * from goods_comment where goods_key=#{id}")
    List<GoodsAndComment> findByGoods(Integer id);

    @Select("select * from goods_comment where comment_key=#{id}")
    List<GoodsAndComment> findByComment(Integer id);

    @Delete("delete from goods_comment where goods_key=#{id}")
    int deleteByGoods(Integer id);

    @Delete("delete from goods_comment where comment_key=#{id}")
    int deleteByComment(Integer id);

    @Insert("insert into goods_comment(goods_key, comment_key)" +
            "values(#{goods_key}, #{comment_key})")
    int insert(GoodsAndComment goodsAndComment);
}
