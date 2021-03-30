package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 20:14
 */
@Data
public class Task {
    // 主键
    private Long id;

    // 团队id
    private Long group_id;

    // 发布时间
    private Timestamp publishTime;

    // 发布者id
    private String publishUserId;

    // 任务开始时间
    private Timestamp startTime;

    // 任务结束时间
    private Timestamp endTime;

    // 任务内容
    private String content;

    // 任务状态: 0: 未完成; 1: 已完成
    private Integer state;

    // 任务类型: 0: 团队任务; 1: 个人任务
    private Integer type;
}
