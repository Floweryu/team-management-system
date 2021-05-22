package com.buct.team.manage.controller.vo;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/4/10 19:18
 */
@Data
public class UserLoginInfoVo {
    private String userId;
    private String username;
    private String createUid;
    private String token;
    private String role;
    private String roleName;
    private String picPath;
}
