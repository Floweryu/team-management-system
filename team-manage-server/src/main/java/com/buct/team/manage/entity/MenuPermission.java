package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/30 14:47
 */
@Data
public class MenuPermission {
    // 主键
    private Long id;

    // 权限id
    private Long permissionId;

    // 菜单id
    private Long menuId;
}
