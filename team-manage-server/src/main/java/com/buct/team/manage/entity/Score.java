package com.buct.team.manage.entity;

import lombok.Data;

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
}
