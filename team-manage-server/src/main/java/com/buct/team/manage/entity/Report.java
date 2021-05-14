package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 15:39
 */
@Data
public class Report {
    // 自增主键
    private Long id;

    // 报告类型
    private Integer type;

    // 已完成任务
    private String finishedTask;

    // 未完成任务
    private String unfinishedTask;

    // 计划任务
    private String planTask;

    // 自我评价
    private String selfReview;

    // 发布者id
    private String userId;

    private String username;

    // 发布时间
    private Timestamp createTime;

}
