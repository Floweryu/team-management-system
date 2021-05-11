package com.buct.team.manage.service;

import com.buct.team.manage.entity.Group;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 16:32
 */
public interface GroupService {
    List<Group> getAllGroup();

    boolean insertGroup(Group group);

    boolean updateGroup(Group group);

    boolean deleteGroup(List<Long> idList);

    List<Group> selectByName(String name);

    Group getGroupById(Long id);
}
