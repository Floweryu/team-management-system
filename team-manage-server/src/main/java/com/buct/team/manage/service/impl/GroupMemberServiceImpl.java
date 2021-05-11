package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.GroupMemberMapper;
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
}
