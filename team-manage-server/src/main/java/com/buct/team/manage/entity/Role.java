package com.buct.team.manage.entity;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/26 15:57
 */
@Data
public class Role {
    // 主键
    private Long id;

    // 角色名称
    private String name;

    // 角色标识
    private String key;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
