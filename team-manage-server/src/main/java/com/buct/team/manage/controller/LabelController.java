package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Label;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/6 22:03
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class LabelController {
    final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    /**
     * 获取所有标签
     */
    @GetMapping("/label/all")
    public Result<List<Label>> getAllLabel() {
        try {
            List<Label> labelList = labelService.getAllLabel();
            log.info("获取的所有标签: {}", labelList);
            return Result.success(labelList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
