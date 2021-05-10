package com.buct.team.manage.controller.dto;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/5/10 21:52
 */
@Data
public class AssetsReq {
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

    // 信息上传修改用户
    private String byUserId;

}
