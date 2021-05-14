package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.TaskMapper;
import com.buct.team.manage.entity.Task;
import com.buct.team.manage.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 22:21
 */
@Service
public class TaskServiceImpl implements TaskService {

    final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> getAllTask() {
        return taskMapper.getAllTask();
    }
    @Override
    public Task getTaskById(Long id) {
        return taskMapper.getTaskById(id);
    }

    @Override
    public boolean addTask(Task task) {
        return taskMapper.addTask(task) > 0;
    }

    @Override
    public boolean deleteTask(List<Long> idList){
        return taskMapper.deleteTask(idList) > 0;
    }

    @Override
    public boolean updateTask(Task task) {
        return taskMapper.updateTask(task) > 0;
    }
}
