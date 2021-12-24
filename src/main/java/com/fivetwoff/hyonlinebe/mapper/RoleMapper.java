package com.fivetwoff.hyonlinebe.mapper;

import com.fivetwoff.hyonlinebe.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 21:39
 */

@Mapper
public interface RoleMapper {
    @Select("select * from role")
    List<Role> findAll();

    @Select("select * from role where id=#{id}")
    Role findById(Integer id);
}
