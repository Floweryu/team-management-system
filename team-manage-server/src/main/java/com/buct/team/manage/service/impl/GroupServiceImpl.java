package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.GroupMapper;
import com.buct.team.manage.entity.Group;
import com.buct.team.manage.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 16:33
 */
@Service
public class GroupServiceImpl implements GroupService {

    final GroupMapper groupMapper;

    public GroupServiceImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public List<Group> getAllGroup() {
        return groupMapper.getAllGroup();
    }

    @Override
    public boolean insertGroup(Group group) {
        return groupMapper.insertGroup(group) > 0;
    }

    @Override
    public boolean updateGroup(Group group) {
        return groupMapper.updateGroup(group) > 0;
    }

    @Override
    public boolean deleteGroup(List<Long> idList) {
        return groupMapper.deleteGroup(idList) > 0;
    }

    @Override
    public List<Group> selectByName(String name) {
        return groupMapper.selectByName(name);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupMapper.getGroupById(id);
    }
}
