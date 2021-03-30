package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/30 19:19
 */
@Data
public class GroupMember {
    // 主键
    private Long id;

    // 团队Id
    private Long groupId;

    // 成员id
    private String userId;
}
