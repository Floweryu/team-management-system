package com.buct.team.manage.entity;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/26 14:14
 */
@Data
public class User {
    // 登录账号（学号）主键
    private String userId;

    // 登录密码
    private String password;

    // 用户名（姓名）
    private String username;

    // 是否被删除：true: 被删除; false: 未删除
    private Boolean delete;

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

    // 登录时间
    private Timestamp loginTime;

    // 上次登录时间
    private Timestamp lastLoginTime;

    // 登录次数
    private Long loginCount;

    // 创建者uid
    private String createUid;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

}
