package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 22:10
 */
@Repository
@Mapper
public interface TaskMapper {
    List<Task> getAllTask();

    Task getTaskById(@Param("id") Long id);

    int addTask(Task task);

    int deleteTask(List<Long> idList);

    int updateTask(Task task);
}
