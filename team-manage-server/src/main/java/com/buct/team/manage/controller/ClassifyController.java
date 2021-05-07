package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Classify;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.ClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/6 21:39
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class ClassifyController {
    final ClassifyService classifyService;

    public ClassifyController(ClassifyService classifyService) {
        this.classifyService = classifyService;
    }

    /**
     * 获取所有分类
     */
    @GetMapping("/classify/all")
    public Result<List<Classify>> getAllClassify() {
        try {
            List<Classify> classifyList = classifyService.getAllClassify();
            log.info("获取的所有分类: {}", classifyList);
            return Result.success(classifyList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
