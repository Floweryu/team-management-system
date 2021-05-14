package com.buct.team.manage.service;

import com.buct.team.manage.entity.TaskMember;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 11:02
 */
public interface TaskMemberService {
    List<String> getUserIdByTaskId(Long id);

    boolean deleteMember(String userId, Long taskId);

    boolean addMember(TaskMember taskMember);
}
