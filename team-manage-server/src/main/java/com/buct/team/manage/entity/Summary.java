package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 19:33
 */
@Data
public class Summary {
    // 主键
    private Long id;

    // 报告者id
    private String userId;

    // 报告者姓名
    private String username;

    // 报告内容
    private String content;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

}
