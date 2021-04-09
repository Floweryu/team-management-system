package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.AdminReq;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/9 14:09
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public Result<List<User>> getAllUser() {
        try {
            List<User> userList = userService.getAllUser();
            log.info("获取的所用用户: {}", userList);
            return Result.success(userList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
