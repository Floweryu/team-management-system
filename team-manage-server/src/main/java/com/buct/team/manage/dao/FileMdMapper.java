package com.buct.team.manage.dao;

import com.buct.team.manage.entity.FileMd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/21 15:24
 */
@Mapper
@Repository
public interface FileMdMapper {

    List<String> getFilePathByMd(@Param("fileMd") String md5);

    int insertFilePathMd(FileMd fileMd);
}
