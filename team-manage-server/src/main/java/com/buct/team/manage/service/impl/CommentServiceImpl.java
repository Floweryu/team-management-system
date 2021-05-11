package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.CommentMapper;
import com.buct.team.manage.entity.Comment;
import com.buct.team.manage.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 13:37
 */
@Service
public class CommentServiceImpl implements CommentService {
    final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment) > 0;
    }

    @Override
    public List<Comment> getCommentByTopicId(Long id, Integer type) {
        return commentMapper.getCommentByTopicId(id, type);
    }

    @Override
    public boolean deleteComment(Long id) {
        return commentMapper.deleteComment(id) > 0;
    }
}
