package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Floweryu
 * @date 2021/3/30 20:14
 */
@Data
public class Task {
    // 主键
    private Long id;

    // 发布者id
    private String publishUserId;

    // 任务开始时间
    private Date startTime;

    // 任务结束时间
    private Date endTime;

    // 任务内容
    private String content;

    // 任务状态: 0未完成 1正在进行 2已完成
    private Integer state;

    // 发布时间
    private Timestamp createTime;
}
