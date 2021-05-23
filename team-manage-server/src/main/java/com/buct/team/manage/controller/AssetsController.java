package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.controller.dto.AssetsReq;
import com.buct.team.manage.entity.Assets;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.AssetsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 22:27
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class AssetsController {
    final AssetsService assetsService;

    public AssetsController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @Log(value = "获取所有资产")
    @GetMapping("/assets")
    public Result<List<Assets>> getAllAssets() {
        try {
            List<Assets> assetsList = assetsService.getAllAssets();
            return Result.success(assetsList);
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
    @Log(value = "通过上传者查询资产")
    @GetMapping("/assets/byUploadUser")
    public Result<List<Assets>> getAssetsByUploadUser(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("传递参数: {}", userId);
        try {
            List<Assets> assetsList = assetsService.getAssetsByUploadUser(userId);
            return Result.success(assetsList);
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
    @Log(value = "通过作者id查询资产")
    @GetMapping("/assets/byUser")
    public Result<List<Assets>> getAssetsByUser(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("传递参数: {}", userId);
        try {
            List<Assets> assetsList = assetsService.getAssetsByUser(userId);
            return Result.success(assetsList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 添加
     * @param assetsReq    参数
     * @return  CodeMsg
     */
    @Log(value = "添加资产")
    @PostMapping("/assets")
    public Result<CodeMsg> addAssets(@RequestBody AssetsReq assetsReq) {
        String name = assetsReq.getName();
        if (name.isEmpty()) {
            return Result.error(400, "assets name can not be null");
        }
        String userId = assetsReq.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        String byUserId = assetsReq.getByUserId();
        if (!StringUtils.isNumeric(byUserId)) {
            return Result.error(400, "byUserId must be Number!");
        }
        if (byUserId.length() < 8 || byUserId.length() > 10) {
            return Result.error(400, "byUserId length should be 8 ~ 10!");
        }
        log.info("添加参数： {}", assetsReq);
        boolean flag = assetsService.insertAssets(assetsReq);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 更新信息
     * @param assetsReq    成果参数
     * @return  CodeMsg
     */
    @Log(value = "更新资产")
    @PutMapping("/assets")
    public Result<CodeMsg> updateAchievement(@RequestBody AssetsReq assetsReq) {
        String name = assetsReq.getName();
        if (name.isEmpty()) {
            return Result.error(400, "assets name can not be null");
        }
        String userId = assetsReq.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        String byUserId = assetsReq.getByUserId();
        if (!StringUtils.isNumeric(byUserId)) {
            return Result.error(400, "byUserId must be Number!");
        }
        if (byUserId.length() < 8 || byUserId.length() > 10) {
            return Result.error(400, "byUserId length should be 8 ~ 10!");
        }
        log.info("添加参数： {}", assetsReq);
        boolean flag = assetsService.updateAssets(assetsReq);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "删除资产")
    @DeleteMapping("/assets")
    public Result<CodeMsg> deleteAssets(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = assetsService.deleteAssets(idList);
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
