package com.buct.team.manage.service;

import com.buct.team.manage.entity.Comment;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 13:36
 */
public interface CommentService {
    boolean addComment(Comment comment);

    List<Comment> getCommentByTopicId(Long id, Integer type);

    boolean deleteComment(Long id);

}
