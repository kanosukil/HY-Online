package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndRole;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserRoleMapper;
import com.fivetwoff.hyonlinebe.service.RoleService;
import com.fivetwoff.hyonlinebe.service.UserService;
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
    private UserService user;
    @Autowired
    private RoleService role;

    public List<UserAndRole> findByUser(Integer id) {
        return userRole.findByUser(id);
    }

    public List<UserAndRole> findByRole(Integer id) {
        return userRole.findByRole(id);
    }

    public boolean deleteByUser(Integer id) {
        try {
            userRole.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteByRole(Integer id) {
        try {
            userRole.deleteByRole(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
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
        return true;
    }
}
