package com.buct.team.manage.controller;

import com.buct.team.manage.controller.vo.GroupMemberVo;
import com.buct.team.manage.entity.Group;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.GroupMemberService;
import com.buct.team.manage.service.GroupService;
import com.buct.team.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    public Result<GroupMemberVo> getAllGroupsById(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id can not < 0");
        }
        try {
            // 通过团队id查询关联的用户id
            List<String> userIdList = groupMemberService.getUserIdByGroupId(id);
            // 获取该团队成员信息
            List<User> userList;
            if (userIdList.size() != 0) {
                userList = userService.getUserByUserIdList(userIdList);
            } else {
                userList = null;
            }
            Group group = groupService.getGroupById(id);
            GroupMemberVo groupMemberVo = new GroupMemberVo();
            groupMemberVo.setGroup(group);
            groupMemberVo.setUserList(userList);

            log.info("获取的团队: {}", groupMemberVo);
            return Result.success(groupMemberVo);
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
