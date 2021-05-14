package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 16:05
 */
@Repository
@Mapper
public interface ReportMapper {
    List<Report> getAllReport();

    int deleteReport(List<Long> id);

    Report getReportById(Long id);

    int addReport(Report report);

    int updateReport(Report report);
}
