package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/26 16:02
 */
@Data
public class UserRole {
    // 主键
    private Long id;

    // 用户id
    private String userId;

    // 角色id
    private Long roleId;
}
