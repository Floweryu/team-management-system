package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 14:45
 */
@Data
public class Menu {
    // 主键
    private Long id;

    // 菜单名称
    private String name;

    // 菜单路径
    private String path;

    // 父级菜单id
    private Long parentId;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
