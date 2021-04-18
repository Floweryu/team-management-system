package com.buct.team.manage.controller.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/4/17 17:19
 */
@Data
public class DocumentReq {
    // 自增主键
    private Long id;

    // 文献标题
    private String title;

    // 作者
    private String author;

    // 发表时间
    private Date pubishTime;

    // 文献简介
    private String introduction;

    // 出版单位
    private String publishPlace;

    // 文献大小，单位(M)
    private Double size;

    // 上传用户
    private String uploadUserId;

    // 上传时间
    private Timestamp uploadTime;

    // 来源地址
    private String originAddress;

    // 点赞数
    private Long likes;

    // 浏览量
    private Long views;

    // 下载数
    private Long downloadTimes;

    // 存储位置
    private String storePath;

    // 文献评分
    private Double score;

    // 评论数
    private Long commentCount;
}
