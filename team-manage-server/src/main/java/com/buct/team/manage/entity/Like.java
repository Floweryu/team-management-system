package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/5/5 20:12
 */
@Data
public class Like {

    private Long id;

    // 文献id
    private Long documentId;

    // 评分者id
    private String userId;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
