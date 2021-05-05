package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.ScoreReq;
import com.buct.team.manage.dao.ScoreMapper;
import com.buct.team.manage.entity.Score;
import com.buct.team.manage.service.ScoreService;
import org.springframework.stereotype.Service;

/**
 * @author Floweryu
 * @date 2021/5/4 21:04
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    final ScoreMapper scoreMapper;

    public ScoreServiceImpl(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Override
    public Double getAverageScore(Long id) {
        return scoreMapper.getAverageScore(id);
    }

    @Override
    public boolean getDuplicateScore(Long documentId, String userId) {
        return scoreMapper.getDuplicateScore(documentId, userId) == 1;
    }

    @Override
    public boolean addScore(ScoreReq scoreReq) {
        Long documentId = scoreReq.getDocumentId();
        String userId = scoreReq.getUserId();
        Integer scoreNumber = scoreReq.getScore();

        Score score = new Score();
        score.setDocumentId(documentId);
        score.setScore(scoreNumber);
        score.setUserId(userId);

        return scoreMapper.addScore(score) > 0;
    }
}
