package com.buct.team.manage.controller.vo;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/5/16 13:37
 */
@Data
public class UserVo {
    private String userId;

    private String username;

    //用户角色主键
    private Long userRoleId;

    private Long roleId;

    // 角色名称
    private String name;

    // 角色标识
    private String key;
}
