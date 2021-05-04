package com.buct.team.manage.dao;

import com.buct.team.manage.controller.vo.UserLoginInfoVo;
import com.buct.team.manage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/9 13:55
 */
@Mapper
@Repository
public interface UserMapper {
    // 用户登录
    UserLoginInfoVo verifyPassword(@Param("userId") String userId, @Param("password") String password);

    // 获取所有用户
    List<User> getAllUser();

    // 通过用户id获取用户信息
    User getUserById(Long id);

    // 添加用户
    int insertUser(User user);

    // 更新用户
    int updatetUser(User user);

    // 删除用户
    int deleteUser(List<Long> idList);

    // 根据账号查询用户
    List<User> getUserByUserId(String userId);

    // 根据用户姓名查询
    List<User> getUserByUserName(String username);

    // 根据用户身份查询
    List<User> getUserByIdentity(Integer identity);

}
