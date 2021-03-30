package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/30 19:22
 */
@Data
public class Label {
    // 主键
    private Long id;

    // 标签名
    private String name;

    // 标签描述
    private String description;
}
