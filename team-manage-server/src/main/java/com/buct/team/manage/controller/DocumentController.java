package com.buct.team.manage.controller;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.controller.vo.DocumentDetailVo;
import com.buct.team.manage.entity.Classify;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.entity.FileMd;
import com.buct.team.manage.entity.Label;
import com.buct.team.manage.enums.FilePathEnum;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.*;
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
    private final DocumentClassifyService documentClassifyService;
    private final ClassifyService classifyService;
    private final DocumentLabelService documentLabelService;
    private final LabelService labelService;
    private final ScoreService scoreService;
    private final LikeService likeService;

    public DocumentController(DocumentService documentService,
                              FileMdService fileMdService,
                              DocumentClassifyService documentClassifyService,
                              ClassifyService classifyService,
                              DocumentLabelService documentLabelService,
                              LabelService labelService,
                              ScoreService scoreService,
                              LikeService likeService) {
        this.documentService = documentService;
        this.fileMdService = fileMdService;
        this.documentClassifyService = documentClassifyService;
        this.classifyService = classifyService;
        this.documentLabelService = documentLabelService;
        this.labelService = labelService;
        this.scoreService = scoreService;
        this.likeService = likeService;
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
     */
    @GetMapping("/document/download")
    public void downloadDocument(HttpServletResponse response, @RequestParam String fileName, @RequestParam Long id) throws UnsupportedEncodingException {
        File file = new File(filePath + System.getProperty("file.separator") + fileName);
        if (file.exists()) {

            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "UTF-8"));
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
                Long downloadCount = documentService.getDownloadCount(id);
                DocumentReq documentReq = new DocumentReq();
                documentReq.setDownloadCount(downloadCount + 1);
                documentReq.setId(id);
                documentService.updateDocument(documentReq);
            } catch (IOException e) {
                log.error("There is something error: {}", e.getMessage());
            }
        }
    }


    /**
     * 根据文献标题查询
     * @param title     文献标题
     */
    @GetMapping("/document/searchByTitle")
    public Result<List<Document>> getByTitle(@RequestParam("title") String title) {
        if (title.isEmpty()) {
            return Result.error(400, "title is not null");
        }
        log.info("传入的参数: {}", title);
        try {
            List<Document> documentList = documentService.selectByTitle(title);
            return Result.success(documentList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据文献作者查询
     * @param author     文献标题
     */
    @GetMapping("/document/searchByAuthor")
    public Result<List<Document>> getByAuthor(@RequestParam("author") String author) {
        if (author.isEmpty()) {
            return Result.error(400, "author is not null");
        }
        log.info("传入的参数: {}", author);
        try {
            List<Document> documentList = documentService.selectByAuthor(author);
            return Result.success(documentList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据文献出版单位查询
     * @param place     文献标题
     */
    @GetMapping("/document/searchByPlace")
    public Result<List<Document>> getByPublishPlace(@RequestParam("place") String place) {
        if (place.isEmpty()) {
            return Result.error(400, "author is not null");
        }
        log.info("传入的参数: {}", place);
        try {
            List<Document> documentList = documentService.selectByPublishPlace(place);
            return Result.success(documentList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 获取文献详情
     * @param id 文献id
     * @param userId    用户id
     * @return Result
     */
    @GetMapping("/document/detail")
    public Result<DocumentDetailVo> getDocumentDetail(@RequestParam("id") Long id, @RequestParam("userId") String userId) {
        if (id < 0) {
            return Result.error(400, "id must >= 0");
        }
        log.info("传递文件id: {}, userId: {}", id, userId);
        try {
            // 根据id查询到document信息
            Document document = documentService.selectById(id);

            // 根据文献id获取与该文献id关联的分类id
            List<Long> classifyId = documentClassifyService.getClassifyId(id);

            List<Classify> classifyList;
            if (classifyId.size() != 0) {
                // 根据类别id查询分类信息
                classifyList = classifyService.getClassifyById(classifyId);
            } else {
                classifyList = null;
            }


            // 根据文献id获取与该文献关联的标签id
            List<Long> labelId = documentLabelService.getLabelId(id);

            List<Label> labelList;
            if (labelId.size() != 0) {
                // 根据标签id查询标签信息
                labelList = labelService.getLabelById(labelId);
            } else {
                labelList = null;
            }

            boolean isScore = scoreService.getDuplicateScore(id, userId);
            boolean isLike = likeService.checkDuplicateLike(id, userId);

            // 返回信息
            DocumentDetailVo documentDetailVo = new DocumentDetailVo();
            documentDetailVo.setDocument(document);
            documentDetailVo.setClassifyList(classifyList);
            documentDetailVo.setLabelList(labelList);
            documentDetailVo.setHasScore(isScore);
            documentDetailVo.setHasLike(isLike);

            // 增加阅读量
            DocumentReq documentReq = new DocumentReq();
            documentReq.setId(id);
            documentReq.setViewCount(document.getViewCount() + 1);
            documentService.updateDocument(documentReq);

            return Result.success(documentDetailVo);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据类别查询文献
     * @param id 类别id
     * @return  文献列表
     */
    @GetMapping("/document/classify")
    public Result<List<Document>> getDocumentByClassify(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id should >= 0");
        }

        log.info("查询类别id: {}", id);
        try {
            List<Long> documentIdList = documentClassifyService.getDocumentId(id);

            List<Document> documentList;
            if (documentIdList.size() != 0) {
                documentList = documentService.getDocumentById(documentIdList);
            } else {
                documentList = null;
            }
            return Result.success(documentList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据标签查询文献
     * @param id 类别id
     * @return  文献列表
     */
    @GetMapping("/document/label")
    public Result<List<Document>> getDocumentByLabel(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id should >= 0");
        }

        log.info("查询标签id: {}", id);
        try {
            List<Long> documentIdList = documentLabelService.getDocumentId(id);

            List<Document> documentList;
            if (documentIdList.size() != 0) {
                documentList = documentService.getDocumentById(documentIdList);
            } else {
                documentList = null;
            }
            return Result.success(documentList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
