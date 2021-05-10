package com.buct.team.manage.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Floweryu
 * @date 2021/5/10 13:31
 */
@Data
public class AchievementReq {
    private Long id;

    // 文章名
    private String articleName;

    // 期刊名
    private String magazineName;

    // 投稿时间
    private Date submitTime;

    // 审核时间
    private Date checkTime;

    // 接收时间
    private Date receiveTime;

    // 花费
    private BigDecimal cost;

    // 奖励
    private BigDecimal reward;

    // 投稿人
    private String userId;

    // 备注
    private String remark;

    // 信息上传修改用户
    private String byUserId;
}
