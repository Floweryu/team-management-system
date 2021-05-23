package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.entity.GroupMember;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.GroupMemberService;
import com.buct.team.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/12 12:37
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class GroupMemberController {
    final GroupMemberService groupMemberService;
    final UserService userService;

    public GroupMemberController(GroupMemberService groupMemberService,
                                 UserService userService) {
        this.groupMemberService = groupMemberService;
        this.userService = userService;
    }

    @Log(value = "添加团队成员")
    @PostMapping("/team/member")
    public Result<CodeMsg> addGroupMember(@RequestBody List<GroupMember> groupMemberList) {
        if (groupMemberList.isEmpty()) {
            return Result.error(400, "group member can not be null!");
        }
        try {
            for (GroupMember groupMember : groupMemberList) {
                String userId = groupMember.getUserId();
                if (!StringUtils.isNumeric(userId)) {
                    return Result.error(400, "userId must be Number!");
                }
                if (userId.length() < 8 || userId.length() > 10) {
                    return Result.error(400, "userId length should be 8 ~ 10!");
                }
                Long groupId = groupMember.getGroupId();
                if (groupId < 0) {
                    return Result.error(400, "group id should >= 0");
                }
                log.info("添加参数： {}", groupMember);
                boolean flag = groupMemberService.addMember(groupMember);
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

    @Log(value = "删除团队成员")
    @DeleteMapping("/teamMember")
    public Result<CodeMsg> deleteGroupMember(@RequestParam String userId, @RequestParam Long groupId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (groupId < 0) {
            return Result.error(400, "group id should >= 0");
        }

        log.info("参数: userId: {}, groupId: {}", userId, groupId);
        try {
            boolean flag = groupMemberService.deleteMember(userId, groupId);
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

    @Log(value = "获取团队成员")
    @GetMapping("/team/member")
    public Result<List<User>> getGroupMember(@RequestParam Long id) {
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
            log.info("获取的成员: {}", userList);
            return Result.success(userList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
