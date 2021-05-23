package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.entity.Class;
import com.buct.team.manage.entity.FileMd;
import com.buct.team.manage.enums.FilePathEnum;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.ClassService;
import com.buct.team.manage.service.FileMdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 13:20
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class ClassController {
    final ClassService classService;
    final FileMdService fileMdService;

    public ClassController(ClassService classService,
                           FileMdService fileMdService) {
        this.classService = classService;
        this.fileMdService = fileMdService;
    }

    private final String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
            + FilePathEnum.FILE_PATH.getPath() + System.getProperty("file.separator")
            + FilePathEnum.CLASS.getPath();

    @Log(value = "获取所有课表")
    @GetMapping("/class")
    public Result<List<Class>> getClass(@RequestParam String userId) {
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("参数: {}", userId);
        try {
            List<Class> classList = classService.getClasses(userId);

            return Result.success(classList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "添加课表")
    @PostMapping("/class")
    public Result<CodeMsg> addClass(@RequestBody Class clas) {
        String name = clas.getName();
        if (name.isEmpty()) {
            return Result.error(400, "Class name can not be null");
        }
        String userId = clas.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("添加参数： {}", clas);
        boolean flag = classService.addClass(clas);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "更新课表")
    @PutMapping("/class")
    public Result<CodeMsg> updateClass(@RequestBody Class clas) {
        String name = clas.getName();
        if (name.isEmpty()) {
            return Result.error(400, "Class name can not be null");
        }
        String userId = clas.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("添加参数： {}", clas);
        boolean flag = classService.updateClass(clas);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "删除课表")
    @DeleteMapping("/class")
    public Result<CodeMsg> deleteAssets(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = classService.deleteClass(idList);
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

    @Log(value = "上传课表")
    @PostMapping("/class/upload")
    public Result<CodeMsg> uploadClass(@RequestParam("file") MultipartFile file, @RequestParam Long id) throws IOException {
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

        String absoluteForPath = filePath + System.getProperty("file.separator") + newFileName;

        boolean isExist;

        if (filePathList.size() != 0) {
            // 获取存储路径
            isExist = true;
            databaseFilePath = filePathList.get(0);
        } else {
            isExist = false;

            databaseFilePath = FilePathEnum.CLASS_PATH.getPath() + newFileName;

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
            File absolutePath = new File(absoluteForPath);

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

        Class clas = new Class();
        clas.setId(id);
        clas.setStorePath(databaseFilePath);
        boolean flag = classService.updateClass(clas);
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
}
