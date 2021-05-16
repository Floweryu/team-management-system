package com.buct.team.manage.controller;

import com.buct.team.manage.controller.vo.UserVo;
import com.buct.team.manage.entity.Role;
import com.buct.team.manage.entity.UserRole;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.RoleService;
import com.buct.team.manage.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/16 11:53
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class RoleController {
    final RoleService roleService;
    final UserRoleService userRoleService;

    public RoleController(RoleService roleService, UserRoleService userRoleService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/roles")
    public Result<List<Role>> getAllRoles() {
        try {
            List<Role> roleList = roleService.getAllRole();
            return Result.success(roleList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/user/role")
    public Result<List<UserVo>> getUserRole() {
        try {
            List<UserVo> userRoleList = userRoleService.getUserAndRole();
            return Result.success(userRoleList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/user/role/byuserId")
    public Result<List<UserVo>> getUserRoleByUserId(@RequestParam String userId) {
        try {
            List<UserVo> userRoleList = userRoleService.getUserAndRoleByUserId(userId);
            return Result.success(userRoleList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @DeleteMapping("/role/user")
    public Result<CodeMsg> deleteAssets(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = userRoleService.deleteUserRole(idList);
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

    @PostMapping("/role/user")
    public Result<CodeMsg> addUserRole(@RequestBody UserRole userRole) {
        String userId = userRole.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "uploadUserId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "uploadUserId length should be 8 ~ 10!");
        }
        Long roleId = userRole.getRoleId();
        if (roleId <= 0 || roleId > 2) {
            return Result.error(400, "roleId should be 1 or 2");
        }
        boolean flag = userRoleService.addUserRole(userRole);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PutMapping("/role/user")
    public Result<CodeMsg> updateUserRole(@RequestBody UserRole userRole) {
        String userId = userRole.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "uploadUserId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "uploadUserId length should be 8 ~ 10!");
        }
        Long roleId = userRole.getRoleId();
        if (roleId <= 0 || roleId > 2) {
            return Result.error(400, "roleId should be 1 or 2");
        }
        boolean flag = userRoleService.updateUserRole(userRole);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
