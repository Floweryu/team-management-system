package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/30 19:53
 */
@Data
public class MeetingSummary {
    // 主键
    private Long id;

    // 会议id
    private Long meetingId;

    // 报告id
    private Long summaryId;
}
