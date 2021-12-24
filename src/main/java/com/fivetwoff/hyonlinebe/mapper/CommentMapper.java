package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:10
 */

@Mapper
public interface CommentMapper {
    @Select("select * from comment")
    List<Comment> findAll();

    @Select("select * from comment where id=#{id}")
    Comment findById(Integer id);

    @Delete("delete from comment where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into comment(id, content)" +
            "values(#{id}, #{content})")
    int insert(Comment comment);

    @Update("update comment" +
            "       set content=#{content}" +
            "       where id=#{id}")
    int updateByPrimaryKey(Comment comment);
}
