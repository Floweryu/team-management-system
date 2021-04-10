package com.buct.team.manage.controller.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Floweryu
 * @date 2021/4/10 14:27
 */
@Data
public class UserReq {
    // 登录账号（学号）
    private String userId;

    // 登录密码
    private String password;

    // 用户名（姓名）
    private String username;

    // 是否被删除：true: 被删除; false: 未删除
    private Boolean deleted;

    // 性别：1：男，0：女
    private Boolean sex;

    // 电子邮箱
    private String email;

    // 手机号
    private String mobile;

    // QQ号
    private String qqOpenId;

    // 微信号
    private String wxOpenId;

    // 出生日期
    private Date birth;

    // 创建者id
    private String createUid;
}
