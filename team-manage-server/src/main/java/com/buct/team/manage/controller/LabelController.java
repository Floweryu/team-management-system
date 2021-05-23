package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.entity.DocumentLabel;
import com.buct.team.manage.entity.Label;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.DocumentLabelService;
import com.buct.team.manage.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    final DocumentLabelService documentLabelService;

    public LabelController(LabelService labelService, DocumentLabelService documentLabelService) {
        this.labelService = labelService;
        this.documentLabelService = documentLabelService;
    }

    /**
     * 获取所有标签
     */
    @Log(value = "获取所有标签")
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

    @Log(value = "通过用户id查询标签")
    @GetMapping("/label/all/byUserId")
    public Result<List<Label>> getAllLabelByUserId(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }

        try {
            List<Label> labelList = labelService.getAllLabelByUserId(userId);
            log.info("获取的所有分类: {}", labelList);
            return Result.success(labelList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "添加标签")
    @PostMapping("/label")
    public Result<CodeMsg> addClassify(@RequestBody Label label) {
        String userId = label.getByUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (label.getName() == null) {
            return Result.error(400, "classify name can not be null");
        }
        log.info("添加参数： {}", label);
        boolean flag = labelService.addLabel(label);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "更新标签")
    @PutMapping("/label")
    public Result<CodeMsg> updateClassify(@RequestBody Label label) {
        String userId = label.getByUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (label.getName() == null) {
            return Result.error(400, "classify name can not be null");
        }
        log.info("添加参数： {}", label);
        boolean flag = labelService.updateLabel(label);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }


    @Log(value = "删除标签")
    @DeleteMapping("/label")
    public Result<CodeMsg> deleteClassify(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = labelService.deleteLabel(idList);
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


    @Log(value = "为文献添加标签")
    @PostMapping("/label/member")
    public Result<CodeMsg> addLabelClassify(@RequestBody List<DocumentLabel> documentLabelList) {
        if (documentLabelList.isEmpty()) {
            return Result.error(400, "group member can not be null!");
        }
        try {
            for (DocumentLabel documentLabel : documentLabelList) {
                Long labelId = documentLabel.getLabelId();
                if (labelId < 0) {
                    return Result.error(400, "label id should >= 0");
                }
                Long documentId = documentLabel.getDocumentId();
                if (documentId < 0) {
                    return Result.error(400, "document id should >= 0");
                }
                log.info("添加参数： {}", documentLabel);
                boolean flag = documentLabelService.addDocumentLabel(documentLabel);
                if (!flag) {
                    return Result.error(CodeMsg.SERVER_ERROR);
                }
            }
            return Result.success(CodeMsg.SUCCESS);
        }catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
