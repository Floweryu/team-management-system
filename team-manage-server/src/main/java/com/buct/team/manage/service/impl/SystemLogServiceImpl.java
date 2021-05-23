package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.SystemLogMapper;
import com.buct.team.manage.entity.SystemLog;
import com.buct.team.manage.service.SystemLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/23 15:18
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {

    final SystemLogMapper systemLogMapper;

    public SystemLogServiceImpl(SystemLogMapper systemLogMapper) {
        this.systemLogMapper = systemLogMapper;
    }

    @Override
    public List<SystemLog> getAllSystemlogs() {
        return systemLogMapper.getAllSystemlogs();
    }

    @Override
    public List<SystemLog> getLogByUserId(String userId) {
        return systemLogMapper.getLogByUserId(userId);
    }

    @Override
    public List<SystemLog> getLogByDatetime(String time) {
        return systemLogMapper.getLogByDatetime(time);
    }

    @Override
    public boolean addSystemLog(SystemLog systemLog) {
        return systemLogMapper.addSystemLog(systemLog) > 0;
    }
}
