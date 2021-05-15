package com.buct.team.manage.controller.dto;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/4/9 14:13
 */
@Data
public class AdminReq {
    private String userId;
    private String password;
    private String verifyCode;
}
