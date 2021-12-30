package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.entity.cascade.UserAndRole;
import com.fivetwoff.hyonlinebe.mapper.RoleMapper;
import com.fivetwoff.hyonlinebe.mapper.UserMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:42
 */

@Slf4j
@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRole;
    @Autowired
    private UserMapper user;
    @Autowired
    private RoleMapper role;

    public List<UserAndRole> findByUser(Integer id) {
        return userRole.findByUser(id);
    }

    public List<UserAndRole> findByRole(Integer id) {
        return userRole.findByRole(id);
    }

    public boolean deleteByUser(Integer id) {
        int i = 0;
        try {
            i = userRole.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("user_role删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByRole(Integer id) {
        int i = 0;
        try {
            i = userRole.deleteByRole(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("user_role删除了" + i + "条信息");
        return true;
    }

    public boolean insert(UserAndRole userAndRole) {
        Integer userKey = userAndRole.getUser_key();
        Integer roleKey = userAndRole.getRole_key();
        if (user.findById(userKey) == null || role.findById(roleKey) == null) {
            log.error("user表和role表内没有对应主键");
            return false;
        } else {
            try {
                userRole.insert(userAndRole);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        log.info("user_role插入成功");
        return true;
    }
}
