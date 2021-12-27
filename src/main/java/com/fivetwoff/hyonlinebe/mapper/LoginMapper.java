package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author VHBin
 * @date 2021/12/25 - 23:46
 */

@Mapper
public interface LoginMapper {

    // Security自定义登录
    @Select("select * from user where username=#{username}")
    User queryByName(String name);
}
