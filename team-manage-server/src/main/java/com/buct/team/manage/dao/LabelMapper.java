package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:34
 */
@Repository
@Mapper
public interface LabelMapper {
    List<Label> getLabelById(List<Long> id);

    List<Label> getAllLabel();
}
