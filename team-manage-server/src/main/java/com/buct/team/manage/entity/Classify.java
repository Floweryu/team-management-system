package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 20:19
 */
@Data
public class Classify {
    // 主键
    private Long id;

    // 分类名称
    private String name;

    // 分类描述
    private String description;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
