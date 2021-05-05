package com.buct.team.manage.service;

import com.buct.team.manage.controller.dto.ScoreReq;

/**
 * @author Floweryu
 * @date 2021/5/4 21:02
 */
public interface ScoreService {
    Double getAverageScore(Long id);

    boolean getDuplicateScore(Long documentId, String userId);

    boolean addScore(ScoreReq scoreReq);
}
