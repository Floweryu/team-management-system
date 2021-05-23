package com.buct.team.manage.service;

import com.buct.team.manage.entity.SystemLog;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/23 15:17
 */
public interface SystemLogService {

    List<SystemLog> getAllSystemlogs();

    List<SystemLog> getLogByUserId(String userId);

    List<SystemLog> getLogByDatetime(String time);

    boolean addSystemLog(SystemLog systemLog);
}
