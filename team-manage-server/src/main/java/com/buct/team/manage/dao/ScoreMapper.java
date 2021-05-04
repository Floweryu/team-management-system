package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Floweryu
 * @date 2021/5/4 20:45
 */
@Repository
@Mapper
public interface ScoreMapper {
    Double getAverageScore(@Param("id") Long id);

    Long getScoreCount(@Param("id") Long id);

    int addScore(Score score);

    int getDuplicateScore(@Param("documentId") Long documentId, @Param("userId") String userId);
}
