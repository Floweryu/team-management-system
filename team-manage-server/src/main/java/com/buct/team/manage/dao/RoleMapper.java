package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 21:38
 */
@Mapper
@Repository
public interface RoleMapper {
    Role getRoleByUserId(@Param("userId") String userId);

    List<Role> getAllRole();
}
