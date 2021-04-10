package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.AdminReq;
import com.buct.team.manage.dao.vo.UserLoginInfoVo;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Floweryu
 * @date 2021/4/9 20:24
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final UserService userService;
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<Object> adminLogin(@RequestBody AdminReq adminReq) {
        try {
            UserLoginInfoVo userInfo = userService.verifyPassword(adminReq.getUserId(), adminReq.getPassword());
            log.info("登录用户信息为：username: {}, password: {}", adminReq.getUserId(), adminReq.getPassword());
            if (userInfo != null) {
                return Result.success(userInfo);
            } else {
                return Result.error(CodeMsg.LOGIN_FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
