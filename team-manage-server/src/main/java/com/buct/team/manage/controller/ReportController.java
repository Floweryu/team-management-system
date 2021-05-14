package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Report;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 16:14
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class ReportController {
    final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public Result<List<Report>> getAllReports() {
        try {
            List<Report> reportList = reportService.getAllReport();
            return Result.success(reportList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/report/id")
    public Result<Report> getReportById(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id can not < 0");
        }
        log.info("参数： {}", id);
        try {
            Report report = reportService.getReportById(id);
            return Result.success(report);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PostMapping("/report")
    public Result<CodeMsg> addReport(@RequestBody Report report) {
        String userId = report.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (report.getType() == null) {
            return Result.error(400, "type can not be null");
        }
        if (report.getUsername().isEmpty()) {
            return Result.error(400, "username can not be null");
        }
        log.info("添加参数： {}", report);
        boolean flag = reportService.addReport(report);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PutMapping("/report")
    public Result<CodeMsg> updateReport(@RequestBody Report report) {
        String userId = report.getUserId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (report.getType() == null) {
            return Result.error(400, "type can not be null");
        }
        if (report.getUsername().isEmpty()) {
            return Result.error(400, "username can not be null");
        }
        log.info("更新参数： {}", report);
        boolean flag = reportService.updateReport(report);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }


    @DeleteMapping("/report")
    public Result<CodeMsg> deleteReports(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = reportService.deleteReport(idList);
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
}
