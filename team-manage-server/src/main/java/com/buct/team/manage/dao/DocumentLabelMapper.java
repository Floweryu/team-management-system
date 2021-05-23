package com.buct.team.manage.dao;

import com.buct.team.manage.entity.DocumentLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:36
 */
@Repository
@Mapper
public interface DocumentLabelMapper {
    List<Long> getLabelId(@Param("id") Long id);

    List<Long> getDocumentId(@Param("id") Long id);

    int addDocumentLabel(DocumentLabel documentLabel);
}
