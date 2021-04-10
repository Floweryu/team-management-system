package com.buct.team.manage.dao;

import com.buct.team.manage.dao.vo.UserLoginInfoVo;
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

    // 添加用户
    int insertUser(User user);
}
