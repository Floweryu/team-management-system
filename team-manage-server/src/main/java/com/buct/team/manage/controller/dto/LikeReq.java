package com.buct.team.manage.controller.dto;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/5/5 20:23
 */
@Data
public class LikeReq {
    // 文献id
    private Long documentId;

    // 评分者id
    private String userId;
}
