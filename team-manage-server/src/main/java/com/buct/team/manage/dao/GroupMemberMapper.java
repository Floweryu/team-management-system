package com.buct.team.manage.dao;

import com.buct.team.manage.entity.GroupMember;
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

    int deleteMember(@Param("userId") String userId, @Param("groupId") Long groupId);

    int addMember(GroupMember groupMember);
}
