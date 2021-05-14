package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.ReportMapper;
import com.buct.team.manage.entity.Report;
import com.buct.team.manage.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 16:13
 */
@Service
public class ReportServiceImpl implements ReportService {
    final ReportMapper reportMapper;

    public ReportServiceImpl(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    public List<Report> getAllReport() {
        return reportMapper.getAllReport();
    }

    @Override
    public boolean deleteReport(List<Long> idList) {
        return reportMapper.deleteReport(idList) > 0;
    }

    @Override
    public Report getReportById(Long id) {
        return reportMapper.getReportById(id);
    }

    @Override
    public boolean addReport(Report report) {
        return reportMapper.addReport(report) > 0;
    }

    @Override
    public boolean updateReport(Report report) {
        return reportMapper.updateReport(report) > 0;
    }
}
