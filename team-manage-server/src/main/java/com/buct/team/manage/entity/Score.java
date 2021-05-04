package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 20:09
 */
@Data
public class Score {
    // 主键
    private Long id;

    // 文献id
    private Long documentId;

    // 评分者id
    private String userId;

    // 分数
    private Integer score;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
