package com.buct.team.manage.controller.dto;

import lombok.Data;

import java.sql.Date;

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
    private Date publishTime;

    // 文献简介
    private String introduction;

    // 出版单位
    private String publishPlace;

    // 文献大小，单位(M)
    private Double size;

    // 上传用户
    private String uploadUserId;

    // 来源地址
    private String originAddress;

    // 存储位置
    private String storePath;

    // 文件名
    private String fileName;

    // 浏览量
    private Long viewCount;

    // 下载数
    private Long downloadCount;

    // 分数
    private Double averageScore;

    // 点赞数
    private Long likeCount;
}
