package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/5/23 14:50
 */
@Data
public class SystemLog {
    private Long id;

    private String url;

    private String description;

    private Timestamp requestDate;

    private String requestMethod;

    private String requestBody;

    private String reponseStatus;

    private String userId;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
