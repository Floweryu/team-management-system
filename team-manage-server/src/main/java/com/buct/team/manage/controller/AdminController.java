package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.AdminReq;
import com.buct.team.manage.controller.vo.UserLoginInfoVo;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.UserService;
import com.buct.team.manage.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Floweryu
 * @date 2021/4/9 20:24
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class AdminController {
    private final UserService userService;
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<Object> adminLogin(@RequestBody AdminReq adminReq) {
        String userId = adminReq.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
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
            UserLoginInfoVo userInfo = new UserLoginInfoVo();
            userInfo.setUserId(userId);
            userInfo.setUsername(user.getUsername());
            userInfo.setCreateUid(user.getCreateUid());
            userInfo.setToken(token);
            return Result.success(userInfo);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
