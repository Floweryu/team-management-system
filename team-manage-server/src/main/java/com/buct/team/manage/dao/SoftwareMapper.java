package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Software;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/8 19:55
 */
@Repository
@Mapper
public interface SoftwareMapper {
    List<Software> getAllSoftware();

    List<Software> searchByName(@Param("name") String name);

    int insertSoftware(Software software);

    int updateSoftware(Software software);

    int deleteSoftware(List<Long> idList);
}
