package com.buct.team.manage.service;

import com.buct.team.manage.entity.Report;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 16:12
 */
public interface ReportService {
    List<Report> getAllReport();

    boolean deleteReport(List<Long> id);

    Report getReportById(Long id);

    boolean addReport(Report report);

    boolean updateReport(Report report);
}
