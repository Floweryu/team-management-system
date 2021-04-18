package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.entity.User;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/17 18:51
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * 获取所有文献
     */
    @GetMapping("/documents")
    public Result<List<Document>> getAllDocuments() {
        try {
            List<Document> documentList = documentService.getAllDocument();
            log.info("获取的所有文献: {}", documentList);
            return Result.success(documentList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 添加文献
     * @param documentReq   文献传参
     */
    @PostMapping("/document")
    public Result<CodeMsg> insertDocument(@RequestBody DocumentReq documentReq) {
        try {
            boolean flag = documentService.insertDocument(documentReq);
            if (flag) {
                log.info("添加文献成功，参数为: {}", documentReq);
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
     * 更新文献
     * @param documentReq   文献传参
     */
    @PutMapping("/document")
    public Result<CodeMsg> updateDocument(@RequestBody DocumentReq documentReq) {
        try {
            boolean flag = documentService.updateDocument(documentReq);
            if (flag) {
                log.info("更新文献成功，参数为: {}", documentReq);
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
