package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/26 16:12
 */
@Data
public class RoleMenu {
    // 主键
    private Long id;

    // 角色id
    private Long roleId;

    // 菜单id
    private Long menuId;
}
