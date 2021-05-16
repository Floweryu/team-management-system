package com.buct.team.manage.dao;

import com.buct.team.manage.controller.vo.UserVo;
import com.buct.team.manage.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/16 11:59
 */
@Repository
@Mapper
public interface UserRoleMapper {
    int addUserRole(UserRole userRole);

    int updateUserRole(UserRole userRole);

    List<UserVo> getUserAndRole();

    List<UserVo> getUserAndRoleByUserId(@Param("userId") String userId);

    int deleteUserRole(List<Long> idList);
}
