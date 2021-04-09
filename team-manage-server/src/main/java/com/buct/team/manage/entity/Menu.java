package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

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

    // 父级菜单id
    private Long parentId;

    // 菜单路径
    private String path;

    // 菜单图标
    private String icon;

    // 权限标识
    private String permission;

    // 菜单类型(M目录 C菜单 F按钮)
    private String type;

    // 菜单状态(0隐藏 1显示)
    private Boolean visible;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    private List<Menu> childMenu;
}
