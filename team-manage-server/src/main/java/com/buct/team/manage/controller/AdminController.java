package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.AdminReq;
import com.buct.team.manage.controller.vo.UserLoginInfoVo;
import com.buct.team.manage.entity.Role;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.RoleService;
import com.buct.team.manage.service.UserService;
import com.buct.team.manage.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Floweryu
 * @date 2021/4/9 20:24
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    public AdminController(UserService userService,
                           RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public Result<Object> adminLogin(@RequestBody AdminReq adminReq, HttpServletRequest request) {
        String userId = adminReq.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if(request.getSession(true).getAttribute("verify_code") == null
                || !adminReq.getVerifyCode().toUpperCase().equals(request.getSession(true).getAttribute("verify_code").toString().toUpperCase())){
            return Result.error(401, "验证码错误");
        }
        try {
            User user = userService.selectUserByUserId(userId);
            if (user == null) {
                return Result.error(400, "没有此用户");
            }
            if (!user.getPassword().equals(adminReq.getPassword())) {
                return Result.error(400, "用户名或密码错误");
            }
            log.info("登录用户信息为：username: {}, password: {}", adminReq.getUserId(), adminReq.getPassword());
            String token = JwtUtil.sign(userId);
            Role role = roleService.getRoleByUserId(userId);
            UserLoginInfoVo userInfo = new UserLoginInfoVo();
            userInfo.setUserId(userId);
            userInfo.setUsername(user.getUsername());
            userInfo.setCreateUid(user.getCreateUid());
            userInfo.setToken(token);
            userInfo.setRole(role.getKey());
            userInfo.setRoleName(role.getName());
            userInfo.setPicPath(user.getPicPath());
            return Result.success(userInfo);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
