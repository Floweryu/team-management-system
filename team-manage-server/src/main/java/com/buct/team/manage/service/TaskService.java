package com.buct.team.manage.service;

import com.buct.team.manage.entity.Task;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 22:20
 */
public interface TaskService {
    List<Task> getAllTask();

    Task getTaskById(Long id);

    boolean addTask(Task task);

    boolean deleteTask(List<Long> idList);

    boolean updateTask(Task task);
}
