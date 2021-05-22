package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.ResetPassReq;
import com.buct.team.manage.controller.dto.UserReq;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

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


    /**
     * 获取所有用户信息
     */
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

    /**
     * 通过用户主键获取用户信息
     * @param id    用户主键
     * @return  User
     */
    @GetMapping("/user")
    public Result<User> getUserById(@RequestParam Long id) {
        if (id <= 0) {
            return Result.error(400, "id should > 0 !");
        }
        try {
            User user = userService.getUserById(id);
            log.info("查询用户id: {}, 用户信息：{}", id, user);
            return Result.success(user);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 添加用户
     * @param userReq 用户参数
     * @return Result
     */
    @PostMapping("/user")
    public Result<CodeMsg> addUser(@RequestBody UserReq userReq) {
        try {
            boolean flag = userService.insertUser(userReq);
            if (flag) {
                log.info("添加用户成功，参数为: {}", userReq);
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 更新用户
     * @param userReq 用户参数
     * @return Result
     */
    @PutMapping("/user")
    public Result<CodeMsg> updateUser(@RequestBody UserReq userReq) {
        try {
            boolean flag = userService.updateUser(userReq);
            if (flag) {
                log.info("更新用户成功，参数为: {}", userReq);
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 删除用户 单个删除和批量删除
     * @param idList    删除用户id
     * @return Result
     */
    @DeleteMapping("/user")
    public Result<CodeMsg> deleteUser(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }
        try {
            boolean flag = userService.deleteUser(idList);
            log.info("删除用户的idList: {}", idList);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            }else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据用户账号查询用户
     * @param userId    用户账号
     * @return  Result
     */
    @GetMapping("/getUserByUserId")
    public Result<List<User>> getUserByUserId(String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        try {
            List<User> userList = userService.getUserByUserId(userId);
            log.info("获取的用户: {}", userList);
            return Result.success(userList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
    /**
     * 根据用户姓名查询用户
     * @param username  用户姓名
     * @return  Result
     */
    @GetMapping("/getUserByUserName")
    public Result<List<User>> getUserByUserName(String username) {
        String regex = "^[\\u4e00-\\u9fa5]{0,5}$";
        if (!Pattern.matches(regex, username)) {
            return Result.error(400, "username must be Chinese and length must be 0 ~ 5!");
        }
        try {
            List<User> userList = userService.getUserByUserName(username);
            log.info("获取的用户: {}", userList);
            return Result.success(userList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
    /**
     * 根据用户身份查询用户
     * @param identity  用户身份
     * @return  Result
     */
    @GetMapping("/getUserByIdentity")
    public Result<List<User>> getUserByIdentity(Integer identity) {
        if (identity < 0 || identity > 7) {
            return Result.error(400, "identity must be Number!");
        }
        try {
            List<User> userList = userService.getUserByIdentity(identity);
            log.info("获取的用户: {}", userList);
            return Result.success(userList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PostMapping("/resetPassword")
    public Result<CodeMsg> resetPassword(@RequestBody ResetPassReq resetPassReq) {
        log.info("传递参数: {}", resetPassReq);
        String userId = resetPassReq.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        // 从数据库获取该用户邮箱地址进行验证
        String userEmail = userService.getUserEmailByUserId(userId);
        if (! userEmail.equals(resetPassReq.getEmail())) {
            return Result.error(400, "邮件地址验证错误");
        }
        String password = resetPassReq.getPassword();
        if (!password.equals(resetPassReq.getCheckPassword())) {
            return Result.error(400, "两次密码输入不一致");
        }
        try {
            boolean flag = userService.updateUserPassword(userId, password);
            if (flag) {
                return Result.success(CodeMsg.RESET_PASSWORD_SUCCESS);
            }else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
