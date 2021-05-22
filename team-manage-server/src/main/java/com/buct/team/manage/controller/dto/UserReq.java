package com.buct.team.manage.controller.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Floweryu
 * @date 2021/4/10 14:27
 */
@Data
public class UserReq {
    private Long id;
    // 登录账号（学号）
    private String userId;

    // 登录密码
    private String password;

    // 用户名（姓名）
    private String username;

    // 学生身份(1 大一; 2 大二; 3 大三; 4大四; 5 研一; 6 研二; 7 研三 0 教师)
    private Integer identity;

    // 是否被删除：true: 被删除; false: 未删除
    private Boolean deleted;

    // 性别：1：男，0：女
    private Integer sex;

    // 电子邮箱
    private String email;

    // 手机号
    private String mobile;

    // QQ号
    private String qqOpenId;

    // 微信号
    private String wxOpenId;

    private String picPath;

    // 出生日期
    private Date birth;

    // 创建者id
    private String createUid;
}
