package com.buct.team.manage.service;

import com.buct.team.manage.entity.GroupMember;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 21:53
 */
public interface GroupMemberService {
    List<String> getUserIdByGroupId(Long id);

    boolean deleteMember(String userId, Long groupId);

    boolean addMember(GroupMember groupMember);
}
