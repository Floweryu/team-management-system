package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.MeetingMapper;
import com.buct.team.manage.entity.Meeting;
import com.buct.team.manage.service.MeetingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 14:37
 */
@Service
public class MeetingServiceImpl implements MeetingService {

    final MeetingMapper meetingMapper;

    public MeetingServiceImpl(MeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    @Override
    public List<Meeting> getAllMeeting() {
        return meetingMapper.getAllMeeting();
    }

    @Override
    public boolean addMeeting(Meeting meeting)  {
        return meetingMapper.addMeeting(meeting) > 0;
    }

    @Override
    public boolean deleteMeeting(List<Long> idList) {
        return meetingMapper.deleteMeeting(idList) > 0;
    }

    @Override
    public boolean updateMeeting(Meeting meeting) {
        return meetingMapper.updateMeeting(meeting) > 0;
    }
}
