package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:45
 */

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    @Delete("delete from user where id=#{id}")
    int deleteById(Integer id);

    @Insert("insert into user(id, username, password_hash, head_portrait)" +
            "values(#{id}, #{username}, #{password_hash}, #{head_portrait})")
    int insert(User user);

    @Update("update user" +
            "       set username=#{username}" +
            "           password_hash=#{password_hash}" +
            "           head_portrait=#{head_portrait}" +
            "       where id=#{id}")
    int updateByPrimaryKey(User user);
}
