package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.LikeReq;
import com.buct.team.manage.dao.LikeMapper;
import com.buct.team.manage.entity.Like;
import com.buct.team.manage.service.LikeService;
import org.springframework.stereotype.Service;

/**
 * @author Floweryu
 * @date 2021/5/5 20:16
 */
@Service
public class LikeMapperImpl implements LikeService {

    final LikeMapper likeMapper;

    public LikeMapperImpl(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    @Override
    public boolean checkDuplicateLike(Long documentId, String userId) {
        return likeMapper.checkDuplicateLike(documentId, userId) == 1;
    }

    @Override
    public boolean addLike(LikeReq likeReq) {
        Long documentId = likeReq.getDocumentId();
        String userId = likeReq.getUserId();

        Like like = new Like();
        like.setDocumentId(documentId);
        like.setUserId(userId);

        return likeMapper.addLike(like) > 0;
    }

    @Override
    public boolean deleteLike(LikeReq likeReq) {
        Long documentId = likeReq.getDocumentId();
        String userId = likeReq.getUserId();

        Like like = new Like();
        like.setDocumentId(documentId);
        like.setUserId(userId);
        return likeMapper.deleteLike(like) > 0;
    }
}
