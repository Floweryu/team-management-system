package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 14:58
 */
@Data
public class Comment {
    // 主键
    private Long id;

    // 评论对象id
    private Long topicId;

    // 评论对象主题
    private Integer topicType;

    // 评论内容
    private String content;

    // 评论者id
    private String fromUid;

    // 被评论的id
    private String toUid;

    // 创建时间
    private Timestamp createTime;
}
