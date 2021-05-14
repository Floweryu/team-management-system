package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.TaskMemberMapper;
import com.buct.team.manage.entity.TaskMember;
import com.buct.team.manage.service.TaskMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 11:03
 */
@Service
public class TaskMemberServiceImpl implements TaskMemberService {
    final TaskMemberMapper taskMemberMapper;

    public TaskMemberServiceImpl(TaskMemberMapper taskMemberMapper) {
        this.taskMemberMapper = taskMemberMapper;
    }
    @Override
    public List<String> getUserIdByTaskId(Long id) {
        return taskMemberMapper.getUserIdByTaskId(id);
    }

    @Override
    public boolean deleteMember(String userId, Long taskId) {
        return taskMemberMapper.deleteMember(userId, taskId) > 0;
    }

    @Override
    public boolean addMember(TaskMember taskMember) {
        return taskMemberMapper.addMember(taskMember) > 0;
    }
}
