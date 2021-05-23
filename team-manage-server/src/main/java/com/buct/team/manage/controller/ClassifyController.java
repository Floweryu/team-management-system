package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Classify;
import com.buct.team.manage.entity.DocumentClassify;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.ClassifyService;
import com.buct.team.manage.service.DocumentClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    final DocumentClassifyService documentClassifyService;

    public ClassifyController(ClassifyService classifyService, DocumentClassifyService documentClassifyService) {
        this.classifyService = classifyService;
        this.documentClassifyService = documentClassifyService;
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

    @GetMapping("/classify/all/byUserId")
    public Result<List<Classify>> getAllClassifyByUserId(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }

        try {
            List<Classify> classifyList = classifyService.getAllClassifyByUserId(userId);
            log.info("获取的所有分类: {}", classifyList);
            return Result.success(classifyList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PostMapping("/classify")
    public Result<CodeMsg> addClassify(@RequestBody Classify classify) {
        String userId = classify.getByUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (classify.getName() == null) {
            return Result.error(400, "classify name can not be null");
        }
        log.info("添加参数： {}", classify);
        boolean flag = classifyService.addClassify(classify);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PutMapping("/classify")
    public Result<CodeMsg> updateClassify(@RequestBody Classify classify) {
        String userId = classify.getByUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (classify.getName() == null) {
            return Result.error(400, "classify name can not be null");
        }
        log.info("添加参数： {}", classify);
        boolean flag = classifyService.updateClassify(classify);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }


    @DeleteMapping("/classify")
    public Result<CodeMsg> deleteClassify(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = classifyService.deleteClassify(idList);
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

    @PostMapping("/classify/member")
    public Result<CodeMsg> addDocumentClassify(@RequestBody List<DocumentClassify> documentClassifyList) {
        if (documentClassifyList.isEmpty()) {
            return Result.error(400, "group member can not be null!");
        }
        try {
            for (DocumentClassify documentClassify : documentClassifyList) {
                Long classifyId = documentClassify.getClassifyId();
                if (classifyId < 0) {
                    return Result.error(400, "classify id should >= 0");
                }
                Long documentId = documentClassify.getDocumentId();
                if (documentId < 0) {
                    return Result.error(400, "document id should >= 0");
                }
                log.info("添加参数： {}", documentClassify);
                boolean flag = documentClassifyService.addDocumentClassify(documentClassify);
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
