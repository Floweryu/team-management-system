package com.buct.team.manage.entity;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/3/30 15:43
 */
@Data
public class Document {
    // 自增主键
    private Long id;

    // 文献标题
    private String title;

    // 作者
    private String author;

    // 发表时间
    private Date publishTime;

    // 文献简介
    private String introduction;

    // 出版单位
    private String publishPlace;

    // 文献大小，单位(M)
    private Double size;

    // 存储位置
    private String storePath;

    // 上传用户
    private String uploadUserId;

    // 来源地址
    private String originAddress;

    // 点赞数
    private Long likeCount;

    // 浏览量
    private Long viewCount;

    // 下载数
    private Long downloadCount;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}
