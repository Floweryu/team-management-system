package com.buct.team.manage.service;

import com.buct.team.manage.entity.Meeting;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 14:35
 */
public interface MeetingService {
    List<Meeting> getAllMeeting();

    boolean addMeeting(Meeting meeting);

    boolean deleteMeeting(List<Long> idList);

    boolean updateMeeting(Meeting meeting);
}
