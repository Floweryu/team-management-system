package com.buct.team.manage.controller.vo;

import com.buct.team.manage.entity.Comment;
import lombok.Data;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 14:43
 */
@Data
public class CommentVo {
    private Integer commentCount;

    private List<Comment> commentList;
}
