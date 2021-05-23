package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.controller.dto.AchievementReq;
import com.buct.team.manage.entity.Achievement;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.AchievementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 15:56
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class AchievementController {
    final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    /**
     * 获取所有文献
     * @return  List
     */
    @Log(value = "获取所有成果")
    @GetMapping("/achievements")
    public Result<List<Achievement>> getAllAchievement() {
        try {
            List<Achievement> achievementList = achievementService.getAllAchievement();
            return Result.success(achievementList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 通过上传者id查询
     * @param userId    上传者id
     * @return  List
     */
    @Log(value = "通过上传者id获取成果")
    @GetMapping("/achievement/byUploadUser")
    public Result<List<Achievement>> getAchievementByUploadUser(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("传递参数: {}", userId);
        try {
            List<Achievement> achievementList = achievementService.getAchievementByUploadUser(userId);
            return Result.success(achievementList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 通过作者id查询
     * @param userId  作者id
     * @return  List
     */
    @Log(value = "通过作者id查询成果")
    @GetMapping("/achievement/byUser")
    public Result<List<Achievement>> getAchievementByUser(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("传递参数: {}", userId);
        try {
            List<Achievement> achievementList = achievementService.getAchievementByUser(userId);
            return Result.success(achievementList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 添加个人成果
     * @param achievementReq    参数
     * @return  CodeMsg
     */
    @Log(value = "添加个人成果")
    @PostMapping("/achievement")
    public Result<CodeMsg> addAchievement(@RequestBody AchievementReq achievementReq) {
        String articleName = achievementReq.getArticleName();
        if (articleName.isEmpty()) {
            return Result.error(400, "article name can not be null");
        }
        String userId = achievementReq.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        String byUserId = achievementReq.getByUserId();
        if (!StringUtils.isNumeric(byUserId)) {
            return Result.error(400, "byUserId must be Number!");
        }
        if (byUserId.length() < 8 || byUserId.length() > 10) {
            return Result.error(400, "byUserId length should be 8 ~ 10!");
        }
        log.info("添加参数： {}", achievementReq);
        boolean flag = achievementService.insertAchievement(achievementReq);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 更新信息
     * @param achievementReq    成果参数
     * @return  CodeMsg
     */
    @Log(value = "更新个人成果")
    @PutMapping("/achievement")
    public Result<CodeMsg> updateAchievement(@RequestBody AchievementReq achievementReq) {
        if (achievementReq.getId() < 0) {
            return Result.error(400, "id must >= 0");
        }
        String articleName = achievementReq.getArticleName();
        if (articleName.isEmpty()) {
            return Result.error(400, "article name can not be null");
        }
        String userId = achievementReq.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        String byUserId = achievementReq.getByUserId();
        if (!StringUtils.isNumeric(byUserId)) {
            return Result.error(400, "byUserId must be Number!");
        }
        if (byUserId.length() < 8 || byUserId.length() > 10) {
            return Result.error(400, "byUserId length should be 8 ~ 10!");
        }
        log.info("接收参数： {}", achievementReq);
        boolean flag = achievementService.updatetAchievement(achievementReq);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "删除个人成果")
    @DeleteMapping("/achievement")
    public Result<CodeMsg> deleteAchievement(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = achievementService.deleteAchievement(idList);
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
