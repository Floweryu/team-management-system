package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.RoleMapper;
import com.buct.team.manage.entity.Role;
import com.buct.team.manage.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/15 19:45
 */
@Service
public class RoleServiceImpl implements RoleService {

    final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getRoleByUserId(String userId) {
        return roleMapper.getRoleByUserId(userId);
    }

    @Override
    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }
}
