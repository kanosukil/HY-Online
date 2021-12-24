package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Role;
import com.fivetwoff.hyonlinebe.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/23 - 21:31
 */

@Slf4j
@Service
public class RoleService {
    @Autowired
    private RoleMapper role;

    public List<Role> findAll() {
        return role.findAll();
    }

    public Role findById(Integer id) {
        return role.findById(id);
    }

}
