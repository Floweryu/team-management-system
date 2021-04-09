package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.AdminReq;
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
 * @date 2021/4/9 14:09
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<CodeMsg> adminLogin(@RequestBody AdminReq adminReq) {
        try {
            boolean flag = userService.verifyPassword(adminReq.getUserId(), adminReq.getPassword());
            log.info("登录用户信息为：username: {}, password: {}", adminReq.getUserId(), adminReq.getPassword());
            if (flag) {
                return Result.success(CodeMsg.LOGIN_SUCCESS);
            } else {
                return Result.error(CodeMsg.LOGIN_FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
