package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.AchievementReq;
import com.buct.team.manage.dao.AchievementMapper;
import com.buct.team.manage.entity.Achievement;
import com.buct.team.manage.service.AchievementService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 13:29
 */
@Service
public class AchievementServiceImpl implements AchievementService {

    final AchievementMapper achievementMapper;

    public AchievementServiceImpl(AchievementMapper achievementMapper) {
        this.achievementMapper = achievementMapper;
    }

    @Override
    public List<Achievement> getAllAchievement() {
        return achievementMapper.getAllAchievement();
    }

    @Override
    public List<Achievement> getAchievementByUploadUser(String userId) {
        return achievementMapper.getAchievementByUploadUser(userId);
    }

    @Override
    public List<Achievement> getAchievementByUser( String userId){
        return achievementMapper.getAchievementByUser(userId);
    }

    @Override
    public boolean insertAchievement(AchievementReq achievementReq) {
        Achievement achievement = transAchievement(achievementReq);
        return achievementMapper.insertAchievement(achievement) > 0;
    }

    @Override
    public boolean updatetAchievement(AchievementReq achievementReq) {
        Achievement achievement = transAchievement(achievementReq);
        return achievementMapper.updatetAchievement(achievement) > 0;
    }

    @Override
    public boolean deleteAchievement(List<Long> idList) {
        return achievementMapper.deleteAchievement(idList) > 0;
    }

    private Achievement transAchievement(AchievementReq achievementReq) {
        Long id = achievementReq.getId();
        String articleName = achievementReq.getArticleName();
        String manageName = achievementReq.getMagazineName();
        Date submitTime = achievementReq.getSubmitTime();
        Date checkTime = achievementReq.getCheckTime();
        Date receiveTime = achievementReq.getReceiveTime();
        BigDecimal cost = achievementReq.getCost();
        BigDecimal reward = achievementReq.getReward();
        String userId = achievementReq.getUserId();
        String remark = achievementReq.getRemark();
        String byUserId = achievementReq.getByUserId();

        Achievement achievement = new Achievement();
        if (id != null) {
            achievement.setId(id);
        }
        if (articleName != null) {
            achievement.setArticleName(articleName);
        }
        if (manageName != null) {
            achievement.setMagazineName(manageName);
        }
        if (submitTime != null) {
            achievement.setSubmitTime(submitTime);
        }
        if (checkTime != null) {
            achievement.setCheckTime(checkTime);
        }
        if (receiveTime != null) {
            achievement.setReceiveTime(receiveTime);
        }
        if (cost != null) {
            achievement.setCost(cost);
        }
        if (reward != null) {
            achievement.setReward(reward);
        }
        if (userId != null) {
            achievement.setUserId(userId);
        }
        if (remark != null) {
            achievement.setRemark(remark);
        }
        if (byUserId != null) {
            achievement.setByUserId(byUserId);
        }
        return achievement;
    }
}
