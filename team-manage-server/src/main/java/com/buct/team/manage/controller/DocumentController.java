package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.entity.FileMd;
import com.buct.team.manage.enums.FilePathEnum;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.DocumentService;
import com.buct.team.manage.service.FileMdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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
    private final FileMdService fileMdService;

    public DocumentController(DocumentService documentService, FileMdService fileMdService) {
        this.documentService = documentService;
        this.fileMdService = fileMdService;
    }

    // 文献存储本地磁盘路径
    private final String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
            + FilePathEnum.FILE_PATH.getPath() + System.getProperty("file.separator")
            + FilePathEnum.DOCUMENT.getPath();

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
     * 删除文献
     * @param idList    删除文献的主键
     * @return  CodeMsg
     */
    @DeleteMapping("/document")
    public Result<CodeMsg> deleteDocuemnt(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = documentService.deleteDocument(idList);
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
     * 上传和更新文件
     * @param file  上传文件
     * @param id    记录id
     * @return  Result
     */
    @PostMapping("/document/upload")
    public Result<CodeMsg> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam Long id) throws IOException {

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

        // 获取文件的md5
        String md5 = DigestUtils.md5Hex(file.getBytes());

        List<String> filePathList = fileMdService.getFilePathByMd(md5);

        // 存储到数据库中的path
        String databaseFilePath;

        // 文件名
        String newFileName = file.getOriginalFilename();

        boolean isExist;

        // 文件已经上传过: 更新文献表中文件的存储路径
        // 文件未上传：上传文件，向文件MD表中添加文件映射MD记录, 更新文献表中文件存储路径
        if (filePathList.size() != 0) {
            // 获取存储路径
            isExist = true;
            databaseFilePath = filePathList.get(0);
        } else {
            isExist = false;

            databaseFilePath = FilePathEnum.DOCUMENT_PATH.getPath() + newFileName;

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

            try {
                // 将文件存储到磁盘
                file.transferTo(absolutePath);

                // 更新t_file_md表
                FileMd fileMd = new FileMd();
                fileMd.setFilePath(databaseFilePath);
                fileMd.setFileMd(md5);
                fileMdService.insertFilePathMd(fileMd);
            } catch (Throwable throwable) {
                log.error("存储文件失败，异常信息： {}", throwable.getMessage());
                return Result.error(CodeMsg.SERVER_ERROR);
            }
        }

        // 更新t_document表
        DocumentReq documentReq = new DocumentReq();
        documentReq.setId(id);
        DecimalFormat df = new DecimalFormat("#.00");
        documentReq.setSize(new Double(df.format(size / 1024 / 1024)));
        documentReq.setStorePath(databaseFilePath);
        documentReq.setFileName(newFileName);

        boolean flag = documentService.updateDocument(documentReq);
        log.info("文件更新信息：id: {}, 存储到数据库中路径: {}", id, databaseFilePath);
        if (flag && isExist) {
            return Result.success(0, "上传文件已存在，更新路径成功");
        }
        else if (!isExist && flag) {
            return Result.success(0, "上传文件成功");
        }else {
            return Result.error(500, "文件存储异常");
        }
    }

    /**
     * 下载文件
     * @param response  下载文献设置
     * @param fileName  传入下载文件名
     * @return  CodeMsg
     */
    @GetMapping("/document/download")
    public Result<CodeMsg> downloadDocument(HttpServletResponse response, @RequestParam String fileName) throws UnsupportedEncodingException {
        File file = new File(filePath + System.getProperty("file.separator") + fileName);
        if (! file.exists()) {
            return Result.error(400, "文件不存在");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8") );
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buff = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
            return Result.success(0, "下载成功");
        } catch (IOException e) {
            log.error("There is something error: {}", e.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
