package com.buct.team.manage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Floweryu
 * @date 2021/4/9 13:55
 */
@Mapper
@Repository
public interface UserMapper {
    int verifyPassword(@Param("userId") String userId, @Param("password") String password);
}
