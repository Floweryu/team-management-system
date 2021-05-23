package com.buct.team.manage.dao;

import com.buct.team.manage.entity.SystemLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/23 15:15
 */
@Mapper
@Repository
public interface SystemLogMapper {

    List<SystemLog> getAllSystemlogs();

    List<SystemLog> getLogByUserId(@Param("userId") String userId);

    List<SystemLog> getLogByDatetime(String time);

    int addSystemLog(SystemLog systemLog);
}
