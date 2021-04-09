package com.buct.team.manage.service;

import com.buct.team.manage.entity.User;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/9 14:06
 */
public interface UserService {
    boolean verifyPassword(String userId, String password);

    List<User> getAllUser();
}
