package com.buct.team.manage.dao;

import com.buct.team.manage.entity.DocumentClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/1 22:57
 */
@Repository
@Mapper
public interface DocumentClassifyMapper {
    List<Long> getClassifyId(@Param("id") Long id);

    List<Long> getDocumentId(@Param("id") Long id);

    int addDocumentClassify(DocumentClassify documentClassify);
}
