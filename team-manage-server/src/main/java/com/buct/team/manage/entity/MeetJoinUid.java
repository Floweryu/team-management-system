package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/30 19:23
 */
@Data
public class MeetJoinUid {
    // 主键
    private Long id;

    // 会议id
    private Long meetId;

    // 参会者id
    private String joinUid;
}
