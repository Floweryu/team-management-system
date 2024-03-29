package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.UserReq;
import com.buct.team.manage.dao.UserMapper;
import com.buct.team.manage.controller.vo.UserLoginInfoVo;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
    public boolean updateUserPassword(String userId, String password) {
        return userMapper.updateUserPassword(userId, password) > 0;
    }

    @Override
    public String getUserEmailByUserId(String userId) {
        return userMapper.getUserEmailByUserId(userId);
    }

    @Override
    public UserLoginInfoVo verifyPassword(String userId, String password) {
        return userMapper.verifyPassword(userId, password);
    }

    @Override
    public User selectUserByUserId(String userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean insertUser(UserReq userReq) {
        User user = transUserReq(userReq);
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public boolean updateUser(UserReq userReq) {
        User user = transUserReq(userReq);
        return userMapper.updatetUser(user) > 0;
    }

    @Override
    public boolean deleteUser(List<Long> idList) {
        return userMapper.deleteUser(idList) > 0;
    }

    @Override
    public List<User> getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public List<User> getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public List<User> getUserByIdentity(Integer identity) {
        return userMapper.getUserByIdentity(identity);
    }

    @Override
    public List<User> getUserByUserIdList(List<String> userIdList) {
        return userMapper.getUserByUserIdList(userIdList);
    }

    private User transUserReq(UserReq userReq) {
        Long id = userReq.getId();
        String userId = userReq.getUserId();
        String password = userReq.getPassword();
        String username = userReq.getUsername();
        Integer identity = userReq.getIdentity();
        Boolean deleted = userReq.getDeleted();
        Integer sex = userReq.getSex();
        String email = userReq.getEmail();
        String mobile = userReq.getMobile();
        String qqOpenId = userReq.getQqOpenId();
        String wxOpenId = userReq.getWxOpenId();
        Date birth = userReq.getBirth();
        String createUid = userReq.getCreateUid();
        String picPath = userReq.getPicPath();
        Long loginCount = userReq.getLoginCount();
        Timestamp loginTime = userReq.getLoginTime();
        Timestamp lastLoginTime = userReq.getLastLoginTime();

        User user = new User();
        if (id != null) {
            user.setId(id);
        }
        if (loginCount != null) {
            user.setLoginCount(loginCount);
        }
        if (loginTime != null) {
            user.setLoginTime(loginTime);
        }
        if (lastLoginTime != null) {
            user.setLastLoginTime(lastLoginTime);
        }
        if (picPath != null) {
            user.setPicPath(picPath);
        }
        if (userId != null) {
            user.setUserId(userId);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (username != null) {
            user.setUsername(username);
        }
        if (identity != null) {
            user.setIdentity(identity);
        }
        if (deleted != null) {
            user.setDeleted(deleted);
        }
        if (sex != null) {
            user.setSex(sex);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (mobile != null) {
            user.setMobile(mobile);
        }
        if (qqOpenId != null) {
            user.setQqOpenId(qqOpenId);
        }
        if (wxOpenId != null) {
            user.setWxOpenId(wxOpenId);
        }
        if (birth != null) {
            user.setBirth(birth);
        }
        if (createUid != null) {
            user.setCreateUid(createUid);
        }
        return user;
    }
}
