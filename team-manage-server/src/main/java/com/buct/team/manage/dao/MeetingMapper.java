package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Meeting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 13:30
 */
@Repository
@Mapper
public interface MeetingMapper {
    List<Meeting> getAllMeeting();

    int addMeeting(Meeting meeting);

    int deleteMeeting(List<Long> idList);

    int updateMeeting(Meeting meeting);
}
