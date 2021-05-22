package com.buct.team.manage.entity;

import lombok.Data;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Floweryu
 * @date 2021/3/26 14:14
 */
@Data
public class User {
    // 主键
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

    // 出生日期
    private Date birth;

    private String picPath;

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
