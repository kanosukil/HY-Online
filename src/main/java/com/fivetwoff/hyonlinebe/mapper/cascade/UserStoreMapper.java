package com.fivetwoff.hyonlinebe.mapper.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndStore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/20 - 22:47
 */

@Mapper
public interface UserStoreMapper {
    @Select("select * form user_store where store_key=#{id}")
    List<UserAndStore> findByStore(Integer id);

    @Select("select * form user_store where user_key=#{id}")
    List<UserAndStore> findByUser(Integer id);

    @Delete("delete from user_store where store_key=#{id}")
    int deleteByStore(Integer id);

    @Delete("delete from user_store where user_key=#{id}")
    int deleteByUser(Integer id);

    @Insert("insert into user_store(user_key, store_key)" +
            "values(#{user_key}, #{store_key})")
    int insert(UserAndStore userAndStore);
}
