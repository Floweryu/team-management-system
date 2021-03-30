package com.buct.team.manage.entity;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/26 16:09
 */
@Data
public class Permission {
    // 主键
    private Long id;

    // 权限名称
    private String name;

    // 权限描述
    private String description;

    // 权限标识
    private String code;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
