package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 19:16
 */
@Data
public class Group {
    // 主键
    private Long id;

    // 团队名称
    private String name;

    // 团队介绍
    private String introduce;

    // 工作内容或方向
    private String workDirection;

    private String byUserId;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
