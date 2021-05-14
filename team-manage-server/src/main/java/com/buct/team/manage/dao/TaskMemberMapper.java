package com.buct.team.manage.dao;

import com.buct.team.manage.entity.TaskMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 11:01
 */
@Mapper
@Repository
public interface TaskMemberMapper {
    List<String> getUserIdByTaskId(@Param("taskId") Long id);

    int deleteMember(@Param("userId") String userId, @Param("taskId") Long taskId);

    int addMember(TaskMember taskMember);
}
