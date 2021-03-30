package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 19:26
 */
@Data
public class Meeting {
    // 主键
    private Long id;

    // 组织者id
    private String organizerId;

    // 组织者名字
    private String name;

    // 组会内容
    private String content;

    // 会议开始时间
    private Timestamp startTime;

    // 会议结束时间
    private Timestamp endTime;

    // 会议地点
    private String place;

    // 会议状态: 0: 未开始; 1: 进行中; 2: 已结束
    private Integer state;
}
