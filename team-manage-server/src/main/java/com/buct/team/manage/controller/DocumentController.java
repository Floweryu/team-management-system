package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.enums.FilePathEnum;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        String title = documentReq.getTitle();
        if (title.isEmpty()) {
            return Result.error(400, "title connot be null");
        }
        if (title.length() > 50) {
            return Result.error(400, "title length is too long, should < 50");
        }

        String author = documentReq.getAuthor();
        if (author.isEmpty()) {
            return Result.error(400, "author connot be null");
        }
        if (author.length() > 20) {
            return Result.error(400, "author length should < 20");
        }

        String publishPlace = documentReq.getPublishPlace();
        if (publishPlace.isEmpty()) {
            return Result.error(400, "publish place cannot be null");
        }

        String uploadUserId = documentReq.getUploadUserId();
        if (!StringUtils.isNumeric(uploadUserId)) {
            return Result.error(400, "uploadUserId must be Number!");
        }
        if (uploadUserId.length() < 8 || uploadUserId.length() > 10) {
            return Result.error(400, "uploadUserId length should be 8 ~ 10!");
        }

        try {
            log.info("添加文献参数为: {}", documentReq);
            boolean flag = documentService.insertDocument(documentReq);
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

    /**
     * 上传和更新文件
     * @param file  上传文件
     * @param id    记录id
     * @return  Result
     */
    @PostMapping("/document/upload")
    public Result<CodeMsg> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam Long id){

        if (id < 0) {
            return Result.error(400, "id must >= 0");
        }

        if (file.isEmpty()) {
            return Result.error(400, "file is empty");
        }

        long size = file.getSize();
        if (size / 1024 / 1024 > 20) {
            return Result.error(400, "the size of file should be < 20M !");
        }

        // 文件名
        String newFileName = file.getOriginalFilename();

        // 文件存储路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + FilePathEnum.FILE_PATH.getPath() + System.getProperty("file.separator")
                + FilePathEnum.DOCUMENT.getPath();

        File newFile = new File(filePath);
        if (!newFile.exists() && !newFile.isDirectory()) {
            log.info("file directory is not exits");
            boolean flag = newFile.mkdirs();
            if (flag) {
                log.info("create a directory successed");
            } else {
                log.info("create a directory failed");
            }
        }

        // 文件存储绝对路径
        File absolutePath = new File(filePath + System.getProperty("file.separator") + newFileName);

        // 存储到数据库中的path
        String databaseFilePath = FilePathEnum.DOCUMENT_PATH.getPath() + newFileName;

        try {
            // 将文件存储到磁盘
            file.transferTo(absolutePath);

            DocumentReq documentReq = new DocumentReq();
            documentReq.setId(id);
            documentReq.setSize((double) (size / 1024 / 1024));
            documentReq.setStorePath(databaseFilePath);

            boolean flag = documentService.updateDocument(documentReq);
            log.info("文件更新信息：id: {}, 文件在磁盘位置: {}, 存储到数据库中信息: {}", id, absolutePath, databaseFilePath);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            }else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("上传文件失败，异常信息： {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }
}
