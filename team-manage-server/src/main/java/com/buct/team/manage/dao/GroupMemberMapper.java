package com.buct.team.manage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 21:50
 */
@Repository
@Mapper
public interface GroupMemberMapper {
    List<String> getUserIdByGroupId(@Param("groupId") Long id);
}
