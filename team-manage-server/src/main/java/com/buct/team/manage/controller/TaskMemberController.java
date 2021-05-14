package com.buct.team.manage.controller;

import com.buct.team.manage.entity.TaskMember;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.TaskMemberService;
import com.buct.team.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 11:06
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class TaskMemberController {
    final TaskMemberService taskMemberService;
    final UserService userService;

    public TaskMemberController(TaskMemberService taskMemberService,
                                 UserService userService) {
        this.taskMemberService = taskMemberService;
        this.userService = userService;
    }

    @PostMapping("/task/member")
    public Result<CodeMsg> addTaskMember(@RequestBody List<TaskMember> taskMemberList) {
        if (taskMemberList.isEmpty()) {
            return Result.error(400, "task member can not be null!");
        }
        try {
            for (TaskMember taskMember : taskMemberList) {
                String userId = taskMember.getUserId();
                if (!StringUtils.isNumeric(userId)) {
                    return Result.error(400, "userId must be Number!");
                }
                if (userId.length() < 8 || userId.length() > 10) {
                    return Result.error(400, "userId length should be 8 ~ 10!");
                }
                Long taskId = taskMember.getTaskId();
                if (taskId < 0) {
                    return Result.error(400, "task id should >= 0");
                }
                log.info("添加参数： {}", taskMember);
                boolean flag = taskMemberService.addMember(taskMember);
                if (!flag) {
                    return Result.error(CodeMsg.SERVER_ERROR);
                }
            }
            return Result.success(CodeMsg.SUCCESS);
        }catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @DeleteMapping("/taskMember")
    public Result<CodeMsg> deleteGroupMember(@RequestParam String userId, @RequestParam Long taskId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (taskId < 0) {
            return Result.error(400, "group id should >= 0");
        }

        log.info("参数: userId: {}, groupId: {}", userId, taskId);
        try {
            boolean flag = taskMemberService.deleteMember(userId, taskId);
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

    @GetMapping("/task/member")
    public Result<List<User>> getTaskMember(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id can not < 0");
        }
        try {
            // 通过团队id查询关联的用户id
            List<String> userIdList = taskMemberService.getUserIdByTaskId(id);
            // 获取该团队成员信息
            List<User> userList;
            if (userIdList.size() != 0) {
                userList = userService.getUserByUserIdList(userIdList);
            } else {
                userList = null;
            }
            log.info("获取的成员: {}", userList);
            return Result.success(userList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
