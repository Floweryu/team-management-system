package com.buct.team.manage.dao.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/4/10 19:18
 */
@Data
public class UserLoginInfoVo {
    private String userId;
    private String username;
    private Timestamp loginTime;
    private Long loginCount;
    private String createUid;
}
