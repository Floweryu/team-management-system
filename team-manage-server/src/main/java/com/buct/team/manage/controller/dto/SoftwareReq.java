package com.buct.team.manage.controller.dto;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/5/9 14:51
 */
@Data
public class SoftwareReq {
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
}
