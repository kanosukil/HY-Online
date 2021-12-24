package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 22:43
 */

@Mapper
public interface UserRoleMapper {
    @Select("select * from user_role where user_key=#{id}")
    List<UserAndRole> findByUser(Integer id);

    @Select("select * from user_role where role_key=#{id}")
    List<UserAndRole> findByRole(Integer id);

    @Delete("delete from user_role where role_key=#{id}")
    int deleteByRole(Integer id);

    @Delete("delete from user_role where user_key=#{id}")
    int deleteByUser(Integer id);

    @Insert("insert into user_role(user_key, role_key)" +
            "values(#{user_key}, #{role_key})")
    int insert(UserAndRole userAndRole);
}
