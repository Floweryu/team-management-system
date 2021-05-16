package com.buct.team.manage.service;

import com.buct.team.manage.controller.vo.UserVo;
import com.buct.team.manage.entity.UserRole;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/16 12:33
 */
public interface UserRoleService {
    boolean addUserRole(UserRole userRole);

    boolean updateUserRole(UserRole userRole);

    List<UserVo> getUserAndRole();

    List<UserVo> getUserAndRoleByUserId(String userId);

    boolean deleteUserRole(List<Long> idList);
}
