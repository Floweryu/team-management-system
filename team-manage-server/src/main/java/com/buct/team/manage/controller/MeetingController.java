package com.buct.team.manage.controller;

import com.buct.team.manage.entity.Meeting;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/14 14:39
 */
@RestController
@Slf4j
@RequestMapping("/manage")
public class MeetingController {
    
    final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/meetings")
    public Result<List<Meeting>> getAllMeetings() {
        try {
            List<Meeting> meetingList = meetingService.getAllMeeting();
            return Result.success(meetingList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
    
    @PostMapping("/meeting")
    public Result<CodeMsg> addClass(@RequestBody Meeting meeting) {
        String userId = meeting.getOrganizerId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (meeting.getStartTime() == null) {
            return Result.error(400, "start time can not be null");
        }
        if (meeting.getEndTime() == null) {
            return Result.error(400, "end time can not be null");
        }
        if (meeting.getContent().isEmpty()) {
            return Result.error(400, "content can not be null");
        }
        if (meeting.getPlace().isEmpty()) {
            return Result.error(400, "place can not be null");
        }
        if (meeting.getState() < 0 || meeting.getState() > 2) {
            return Result.error(400, "state should >=0 and <= 2");
        }
        log.info("添加参数： {}", meeting);
        boolean flag = meetingService.addMeeting(meeting);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @PutMapping("/meeting")
    public Result<CodeMsg> updateMeeting(@RequestBody Meeting meeting) {
        String userId = meeting.getOrganizerId();
        if (!StringUtils.isNumeric(userId)) {
            return Result.error(400, "userId must be Number!");
        }
        if (userId.length() < 8 || userId.length() > 10) {
            return Result.error(400, "userId length should be 8 ~ 10!");
        }
        if (meeting.getStartTime() == null) {
            return Result.error(400, "start time can not be null");
        }
        if (meeting.getEndTime() == null) {
            return Result.error(400, "end time can not be null");
        }
        if (meeting.getContent().isEmpty()) {
            return Result.error(400, "content can not be null");
        }
        if (meeting.getPlace().isEmpty()) {
            return Result.error(400, "place can not be null");
        }
        if (meeting.getState() < 0 || meeting.getState() > 2) {
            return Result.error(400, "state should >=0 and <= 2");
        }
        log.info("更新参数： {}", meeting);
        boolean flag = meetingService.updateMeeting(meeting);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }


    @DeleteMapping("/meeting")
    public Result<CodeMsg> deleteAssets(@RequestBody List<Long> idList) {
        if (idList.isEmpty()) {
            return Result.error(400, "idList should not be null !");
        }

        log.info("删除的idList: {}", idList);
        try {
            boolean flag = meetingService.deleteMeeting(idList);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.SERVER_ERROR);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
