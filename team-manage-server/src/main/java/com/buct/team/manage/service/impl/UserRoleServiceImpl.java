package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.vo.UserVo;
import com.buct.team.manage.dao.UserRoleMapper;
import com.buct.team.manage.entity.UserRole;
import com.buct.team.manage.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/16 12:34
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public boolean addUserRole(UserRole userRole) {
        return userRoleMapper.addUserRole(userRole) > 0;
    }

    @Override
    public boolean updateUserRole(UserRole userRole) {
        return userRoleMapper.updateUserRole(userRole) > 0;
    }

    @Override
    public List<UserVo> getUserAndRole(){
        return userRoleMapper.getUserAndRole();
    }

    @Override
    public List<UserVo> getUserAndRoleByUserId(String userId) {
        return userRoleMapper.getUserAndRoleByUserId(userId);
    }

    @Override
    public boolean deleteUserRole(List<Long> idList) {
        return userRoleMapper.deleteUserRole(idList) > 0;
    }
}
