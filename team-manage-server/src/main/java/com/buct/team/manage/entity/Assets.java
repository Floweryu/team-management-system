package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 14:55
 */
@Data
public class Assets {
    // 自增主键
    private Long id;

    // 资产编号
    private String number;

    // 资产名称
    private String name;

    // 使用者id
    private String userId;

    // 备注
    private String remark;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
