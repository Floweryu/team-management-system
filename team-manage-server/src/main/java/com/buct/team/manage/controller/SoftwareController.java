package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.SoftwareReq;
import com.buct.team.manage.entity.Software;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.SoftwareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/9 14:41
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class SoftwareController {
    final SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    /**
     * 获取所有软件信息
     * @return List
     */
    @GetMapping("/softwares")
    public Result<List<Software>> getAllSoftware() {
        try {
            List<Software> softwareList = softwareService.getAllSoftware();
            log.info("获取的所有软件: {}", softwareList);
            return Result.success(softwareList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 添加软件资源
     * @param softwareReq 请求参数
     * @return CodeMsg
     */
    @PostMapping("/software")
    public Result<CodeMsg> insertSoftware(@RequestBody SoftwareReq softwareReq) {
        String name = softwareReq.getName();
        if (name.isEmpty()) {
            return Result.error(400, "software name connot be null");
        }
        String downloadLink = softwareReq.getDownloadLink();
        if (downloadLink.isEmpty()) {
            return Result.error(400, "software downloadLink connot be null");
        }

        log.info("添加软件参数为: {}", softwareReq);
        try {
            boolean flag = softwareService.insertSoftware(softwareReq);
            if (flag) {
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
     * 更新软件资源
     * @param softwareReq   参数
     * @return CodeMsg
     */
    @PutMapping("/software")
    public Result<CodeMsg> updateSoftware(@RequestBody SoftwareReq softwareReq) {
        String name = softwareReq.getName();
        if (name.isEmpty()) {
            return Result.error(400, "software name connot be null");
        }
        String downloadLink = softwareReq.getDownloadLink();
        if (downloadLink.isEmpty()) {
            return Result.error(400, "software downloadLink connot be null");
        }

        log.info("更新软件参数为: {}", softwareReq);
        try {
            boolean flag = softwareService.updateSoftware(softwareReq);
            if (flag) {
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
     * 删除
     * @param idList    删除文献的主键
     * @return  CodeMsg
     */
    @DeleteMapping("/software")
    public Result<CodeMsg> deleteSoftware(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = softwareService.deleteSoftware(idList);
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

    /**
     * 通过软件名查找
     * @param name  软件名
     * @return  List
     */
    @GetMapping("/software/searchByName")
    public Result<List<Software>> getByName(@RequestParam String name) {
        if (name.isEmpty()) {
            return Result.error(400, "name should not be null !");
        }
        log.info("传入的参数: {}", name);
        try {
            List<Software> softwareList = softwareService.searchByName(name);
            return Result.success(softwareList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
