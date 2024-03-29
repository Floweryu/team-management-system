package com.buct.team.manage.service;

import com.buct.team.manage.controller.dto.UserReq;
import com.buct.team.manage.controller.vo.UserLoginInfoVo;
import com.buct.team.manage.entity.User;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/9 14:06
 */
public interface UserService {
    boolean updateUserPassword(String userId, String password);

    String getUserEmailByUserId(String userId);

    UserLoginInfoVo verifyPassword(String userId, String password);

    User selectUserByUserId(String userId);

    List<User> getAllUser();

    User getUserById(Long id);

    boolean insertUser(UserReq userReq);

    boolean updateUser(UserReq userReq);

    boolean deleteUser(List<Long> idList);

    List<User> getUserByUserId(String userId);

    List<User> getUserByUserName(String username);

    List<User> getUserByIdentity(Integer identity);

    List<User> getUserByUserIdList(List<String> userIdList);
}
