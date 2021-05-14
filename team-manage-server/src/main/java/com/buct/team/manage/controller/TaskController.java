package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Task;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 22:23
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Result<List<Task>> getAllTasks() {
        try {
            List<Task> taskList = taskService.getAllTask();
            return Result.success(taskList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/task/id")
    public Result<Task> getTaskById(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id can not < 0");
        }
        log.info("参数： {}", id);
        try {
            Task Task = taskService.getTaskById(id);
            return Result.success(Task);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PostMapping("/task")
    public Result<CodeMsg> addClass(@RequestBody Task task) {
        String userId = task.getPublishUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (task.getStartTime() == null) {
            return Result.error(400, "start time can not be null");
        }
        if (task.getEndTime() == null) {
            return Result.error(400, "end time can not be null");
        }
        if (task.getContent().isEmpty()) {
            return Result.error(400, "content can not be null");
        }
        if (task.getState() < 0 || task.getState() > 2) {
            return Result.error(400, "state should >=0 and <= 2");
        }
        log.info("添加参数： {}", task);
        boolean flag = taskService.addTask(task);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PutMapping("/task")
    public Result<CodeMsg> updateTask(@RequestBody Task task) {
        String userId = task.getPublishUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (task.getStartTime() == null) {
            return Result.error(400, "start time can not be null");
        }
        if (task.getEndTime() == null) {
            return Result.error(400, "end time can not be null");
        }
        if (task.getContent().isEmpty()) {
            return Result.error(400, "content can not be null");
        }
        if (task.getState() < 0 || task.getState() > 2) {
            return Result.error(400, "state should >=0 and <= 2");
        }
        log.info("更新参数： {}", task);
        boolean flag = taskService.updateTask(task);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }


    @DeleteMapping("/task")
    public Result<CodeMsg> deleteAssets(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = taskService.deleteTask(idList);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.SERVER_ERROR);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
