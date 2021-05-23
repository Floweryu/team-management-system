package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.controller.vo.CommentVo;
import com.buct.team.manage.entity.Comment;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 13:38
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class CommentController {

    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Log(value = "添加评论")
    @PostMapping("/comment")
    public Result<CodeMsg> addComment(@RequestBody Comment comment) {
        if (comment.getTopicId() < 0) {
            return Result.error(400, "topic id should be >= 0");
        }

        String userId = comment.getFromUid();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        log.info("添加参数： {}", comment);
        boolean flag = commentService.addComment(comment);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "根据主题获取评论")
    @GetMapping("/comment/byTopicId")
    public Result<CommentVo> getCommentsById(@RequestParam Long id, @RequestParam Integer type) {
        if (id < 0) {
            return Result.error(400, "id can not < 0");
        }
        try {
            List<Comment> commentList = commentService.getCommentByTopicId(id, type);
            // 返回评论信息和评论数量
            CommentVo commentVo = new CommentVo();
            commentVo.setCommentList(commentList);
            commentVo.setCommentCount(commentList.size());
            return Result.success(commentVo);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Log(value = "删除评论")
    @DeleteMapping("/comment")
    public Result<CodeMsg> deleteComment(@RequestParam Long id) {
        if (id < 0) {
            return Result.error(400, "id can not < 0");
        }
        log.info("删除的id: {}", id);
        try {
            boolean flag = commentService.deleteComment(id);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.SERVER_ERROR);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
