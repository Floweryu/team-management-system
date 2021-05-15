package com.buct.team.manage.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 20:11
 */
@Data
public class Software {
    // 主键
    private Long id;

    // 软件名
    private String name;

    // 软件版本号
    private String version;

    // 下载地址
    private String downloadLink;

    // 备注
    private String remark;

    private String uploadUserId;
    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
