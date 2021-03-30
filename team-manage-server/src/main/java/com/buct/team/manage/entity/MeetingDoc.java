package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/30 19:30
 */
@Data
public class MeetingDoc {
    // 主键
    private Long id;

    // 会议id
    private Long meetingId;

    // 文献id;
    private Long documentId;
}
