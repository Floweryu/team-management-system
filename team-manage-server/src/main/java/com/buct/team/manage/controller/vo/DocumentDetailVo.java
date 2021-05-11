package com.buct.team.manage.controller.vo;

import com.buct.team.manage.entity.Classify;
import com.buct.team.manage.entity.Comment;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.entity.Label;
import lombok.Data;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:51
 */
@Data
public class DocumentDetailVo {
    // 文献内容
    private Document document;
    
    // 是否已经评分
    private boolean hasScore;

    // 是否已经点赞
    private boolean hasLike;

    // 文献所属类别
    private List<Classify> classifyList;

    // 文献所属标签
    private List<Label> labelList;

    private List<Comment> commentList;
}
