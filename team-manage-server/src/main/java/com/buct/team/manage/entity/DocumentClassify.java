package com.buct.team.manage.entity;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/3/30 19:07
 */
@Data
public class DocumentClassify {
    private Long id;

    // 文献id
    private Long documentId;

    // 类别id
    private Long classifyId;
}
