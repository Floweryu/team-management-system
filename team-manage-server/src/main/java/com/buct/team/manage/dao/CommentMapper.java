package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 13:30
 */
@Repository
@Mapper
public interface CommentMapper {
    int addComment(Comment comment);

    List<Comment> getCommentByTopicId(@Param("id") Long id, @Param("type") Integer type);

    int deleteComment(@Param("id") Long id);

}
