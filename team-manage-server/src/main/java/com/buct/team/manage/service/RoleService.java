package com.buct.team.manage.service;

import com.buct.team.manage.entity.Role;

/**
 * @author Floweryu
 * @date 2021/5/15 19:45
 */
public interface RoleService {
    Role getRoleByUserId(String userId);
}
