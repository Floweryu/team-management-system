package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.GroupMemberMapper;
import com.buct.team.manage.entity.GroupMember;
import com.buct.team.manage.service.GroupMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 21:53
 */
@Service
public class GroupMemberServiceImpl implements GroupMemberService {
    final GroupMemberMapper groupMemberMapper;

    public GroupMemberServiceImpl(GroupMemberMapper groupMemberMapper) {
        this.groupMemberMapper = groupMemberMapper;
    }

    @Override
    public List<String> getUserIdByGroupId(Long id) {
        return groupMemberMapper.getUserIdByGroupId(id);
    }

    @Override
    public boolean deleteMember(String userId, Long groupId) {
        return groupMemberMapper.deleteMember(userId, groupId) > 0;
    }

    @Override
    public boolean addMember(GroupMember groupMember) {
        return groupMemberMapper.addMember(groupMember) > 0;
    }
}
