package com.buct.team.manage.controller;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.controller.dto.LikeReq;
import com.buct.team.manage.controller.vo.LikeVo;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.DocumentService;
import com.buct.team.manage.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Floweryu
 * @date 2021/5/5 20:22
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class LikeController {
    final LikeService likeService;
    final DocumentService documentService;

    public LikeController(LikeService likeService, DocumentService documentService) {
        this.likeService = likeService;
        this.documentService = documentService;
    }

    /**
     * 添加点赞信息
     * @param likeReq 文献id, 用户id
     * @return Result
     */
    @Log(value = "添加点赞信息")
    @PostMapping("/like/add")
    public Result<Object> addLike(@RequestBody LikeReq likeReq) {
        if (likeReq.getDocumentId() == null){
            return Result.error(400, "document id cannot be null");
        }
        if (likeReq.getUserId() == null) {
            return Result.error(400, "user id cannot be null");
        }
        log.info("点赞参数： {}", likeReq);
        boolean flag = likeService.addLike(likeReq);
        if (flag) {
            // 获取该文献当前点赞数
            Long likeCount = documentService.getLikeCount(likeReq.getDocumentId());

            // 修改文献总点赞数
            DocumentReq documentReq = new DocumentReq();
            documentReq.setId(likeReq.getDocumentId());
            documentReq.setLikeCount(likeCount + 1);
            boolean upFlag = documentService.updateDocument(documentReq);

            // 返回添加后的点赞数和点赞状态
            LikeVo likeVo = new LikeVo();
            likeVo.setLikeNum(likeCount + 1);
            likeVo.setHasLike(true);

            if (upFlag) {
                return Result.success(likeVo);
            } else {
                return Result.error(CodeMsg.SERVER_ERROR);
            }
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 删除点赞信息
     * @param likeReq 文献id, 用户id
     * @return Result
     */
    @Log(value = "删除点赞信息")
    @DeleteMapping("/like/delete")
    public Result<Object> deleteLike(@RequestBody LikeReq likeReq) {
        if (likeReq.getDocumentId() == null){
            return Result.error(400, "document id cannot be null");
        }
        if (likeReq.getUserId() == null) {
            return Result.error(400, "user id cannot be null");
        }
        log.info("删除点赞参数： {}", likeReq);
        boolean flag = likeService.deleteLike(likeReq);
        if (flag) {
            // 获取该文献当前点赞数
            Long likeCount = documentService.getLikeCount(likeReq.getDocumentId());

            // 修改文献总点赞数
            DocumentReq documentReq = new DocumentReq();
            documentReq.setId(likeReq.getDocumentId());
            documentReq.setLikeCount(likeCount - 1);
            boolean upFlag = documentService.updateDocument(documentReq);

            // 返回删除后的点赞数和点赞状态
            LikeVo likeVo = new LikeVo();
            likeVo.setLikeNum(likeCount - 1);
            likeVo.setHasLike(false);
            if (upFlag) {
                return Result.success(likeVo);
            } else {
                return Result.error(CodeMsg.SERVER_ERROR);
            }
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
