package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.ScoreReq;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.DocumentService;
import com.buct.team.manage.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

/**
 * @author Floweryu
 * @date 2021/5/4 21:06
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class ScoreController {
    final ScoreService scoreService;
    final DocumentService documentService;

    public ScoreController(ScoreService scoreService, DocumentService documentService) {
        this.scoreService = scoreService;
        this.documentService = documentService;
    }

    @PostMapping("/score/add")
    public Result<CodeMsg> getAverageScore(@RequestBody ScoreReq score) {
        if (score.getScore() < 0 || score.getScore() > 5) {
            return Result.error(400, "id must be >= 0 and <= 5");
        }
        if (score.getDocumentId() == null){
            return Result.error(400, "document id cannot be null");
        }
        if (score.getUserId() == null) {
            return Result.error(400, "user id cannot be null");
        }

        log.info("添加分数参数： {}", score);
        DecimalFormat df = new DecimalFormat("#.00");
        boolean flag = scoreService.addScore(score);
        if (flag) {
            Double getScore = scoreService.getAverageScore(score.getDocumentId());
            Double average = new Double(df.format(getScore));
            documentService.updateAverageScore(score.getDocumentId(), average);
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
