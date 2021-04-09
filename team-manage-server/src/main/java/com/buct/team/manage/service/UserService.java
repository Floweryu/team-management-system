package com.buct.team.manage.service;

/**
 * @author Floweryu
 * @date 2021/4/9 14:06
 */
public interface UserService {
    boolean verifyPassword(String userId, String password);
}
