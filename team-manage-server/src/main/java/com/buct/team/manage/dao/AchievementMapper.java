package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Achievement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 13:08
 */
@Mapper
@Repository
public interface AchievementMapper {
    List<Achievement> getAllAchievement();
    List<Achievement> getAchievementByUploadUser(@Param("userId") String userId);

    List<Achievement> getAchievementByUser(@Param("userId") String userId);

    int insertAchievement(Achievement achievement);

    int updatetAchievement(Achievement achievement);

    int deleteAchievement(List<Long> idList);
}
