package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.entity.cascade.UserAndComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 22:26
 */

@Mapper
public interface UserCommentMapper {
    @Select("select * from user_comment where user_key=#{id}")
    List<UserAndComment> findByUser(Integer id);

    @Select("select * from user_comment where comment_key=#{id}")
    List<UserAndComment> findByComment(Integer id);

    @Delete("delete from user_comment where user_key=#{id}")
    int deleteByUser(Integer id);

    @Delete("delete from user_comment where comment_key=#{id}")
    int deleteByComment(Integer id);

    @Insert("insert into user_comment(user_key, comment_key)" +
            "values(#{user_key}, #{comment_key})")
    int insert(UserAndComment userAndComment);
}
