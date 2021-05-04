package com.buct.team.manage.controller.dto;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/5/4 21:53
 */
@Data
public class ScoreReq {
    // 文献id
    private Long documentId;

    // 评分者id
    private String userId;

    // 分数
    private Integer score;
}
