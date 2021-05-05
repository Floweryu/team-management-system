package com.buct.team.manage.service;

import com.buct.team.manage.controller.dto.LikeReq;

/**
 * @author Floweryu
 * @date 2021/5/5 20:16
 */
public interface LikeService {
    boolean checkDuplicateLike(Long documentId, String userId);

    boolean addLike(LikeReq likeReq);

    boolean deleteLike(LikeReq likeReq);
}
