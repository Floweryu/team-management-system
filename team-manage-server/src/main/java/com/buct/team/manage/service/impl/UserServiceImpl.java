package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.UserMapper;
import com.buct.team.manage.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Floweryu
 * @date 2021/4/9 14:07
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean verifyPassword(String userId, String password) {
        return userMapper.verifyPassword(userId, password) > 0;
    }
}
