package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.entity.SystemLog;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/23 15:23
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class SystemLogController {

    final SystemLogService systemLogService;

    public SystemLogController(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @Log(value = "获取所有日志")
    @GetMapping("/systemlogs")
    public Result<List<SystemLog>> getAllSystemLog() {
        try {
            List<SystemLog> systemLogList = systemLogService.getAllSystemlogs();
            log.info("获取的所有日志: {}", systemLogList);
            return Result.success(systemLogList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "通过时间查找日志")
    @GetMapping("/systemlogs/byTime")
    public Result<List<SystemLog>> getSystemLogByTime(@RequestParam String time) {
        try {
            List<SystemLog> systemLogList = systemLogService.getLogByDatetime(time);
            log.info("获取的所有日志: {}", systemLogList);
            return Result.success(systemLogList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "通过用户查找")
    @GetMapping("/systemlogs/byUserId")
    public Result<List<SystemLog>> getSystemLogByUser(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        try {
            List<SystemLog> systemLogList = systemLogService.getLogByUserId(userId);
            log.info("获取的所有日志: {}", systemLogList);
            return Result.success(systemLogList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
