package com.buct.team.manage.entity;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/26 16:12
 */
@Data
public class RolePermission {
    // 主键
    private Long id;

    // 角色id
    private Long roleId;

    // 权限id
    private Long permissionId;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
