package com.buct.team.manage.service;

import com.buct.team.manage.controller.dto.AchievementReq;
import com.buct.team.manage.entity.Achievement;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 13:28
 */
public interface AchievementService {

    List<Achievement> getAllAchievement();
    List<Achievement> getAchievementByUploadUser(String userId);

    List<Achievement> getAchievementByUser( String userId);

    boolean insertAchievement(AchievementReq achievementReq);

    boolean updatetAchievement(AchievementReq achievementReq);

    boolean deleteAchievement(List<Long> idList);
}
