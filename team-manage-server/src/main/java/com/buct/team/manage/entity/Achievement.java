package com.buct.team.manage.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 14:49
 */
@Data
public class Achievement {
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

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
