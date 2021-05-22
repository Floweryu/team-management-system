package com.buct.team.manage.controller.dto;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/5/22 14:08
 */
@Data
public class ResetPassReq {
    private String email;

    private String password;

    private String checkPassword;

    private String userId;
}
