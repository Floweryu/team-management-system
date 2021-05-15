package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Group;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.GroupMemberService;
import com.buct.team.manage.service.GroupService;
import com.buct.team.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 18:32
 */

@RestController
@Slf4j
@RequestMapping("/manage")
public class GroupController {
    final GroupService groupService;
    final GroupMemberService groupMemberService;
    final UserService userService;

    public GroupController(GroupService groupService,
                           GroupMemberService groupMemberService,
                           UserService userService) {
        this.groupService = groupService;
        this.groupMemberService = groupMemberService;
        this.userService = userService;
    }

    @GetMapping("/teams")
    public Result<List<Group>> getAllGroups() {
        try {
            List<Group> groupList = groupService.getAllGroup();
            log.info("获取的所有团队: {}", groupList);
            return Result.success(groupList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/teams/userId")
    public Result<List<Group>> getAllGroupsByUserId(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "uploadUserId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "uploadUserId length should be 8 ~ 10!");
        }
        try {
            List<Group> groupList = groupService.getAllGroupByUserId(userId);
            log.info("获取的所有团队: {}", groupList);
            return Result.success(groupList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/team/byName")
    public Result<List<Group>> getAllGroupsByName(@RequestParam String name) {
        if (name.isEmpty()) {
            return Result.error(400, "group name can not be null");
        }
        try {
            List<Group> groupList = groupService.selectByName(name);
            log.info("获取的所有团队: {}", groupList);
            return Result.success(groupList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/team/byTeamId")
    public Result<Group> getAllGroupsById(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id can not < 0");
        }
        try {
            Group group = groupService.getGroupById(id);

            log.info("获取的团队: {}", group);
            return Result.success(group);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PostMapping("/team")
    public Result<CodeMsg> addGroup(@RequestBody Group group) {
        String name = group.getName();
        if (name.isEmpty()) {
            return Result.error(400, "group name can not be null");
        }

        log.info("添加参数： {}", group);
        boolean flag = groupService.insertGroup(group);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 更新信息
     * @param  group   成果参数
     * @return  CodeMsg
     */
    @PutMapping("/team")
    public Result<CodeMsg> updateGroup(@RequestBody Group group) {
        String name = group.getName();
        if (name.isEmpty()) {
            return Result.error(400, "group name can not be null");
        }
        log.info("添加参数： {}", group);
        boolean flag = groupService.updateGroup(group);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @DeleteMapping("/team")
    public Result<CodeMsg> deleteGroup(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = groupService.deleteGroup(idList);
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
