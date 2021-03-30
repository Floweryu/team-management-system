package com.buct.team.manage.entity;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/26 16:02
 */
@Data
public class UserRole {
    // 主键
    private Long id;

    // 用户id
    private Long userId;

    // 角色id
    private Long roleId;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
