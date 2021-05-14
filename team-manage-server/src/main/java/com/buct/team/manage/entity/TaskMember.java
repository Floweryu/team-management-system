package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/5/14 10:58
 */
@Data
public class TaskMember {

    // 主键
    private Long id;

    // 团队Id
    private Long taskId;

    // 成员id
    private String userId;
}
