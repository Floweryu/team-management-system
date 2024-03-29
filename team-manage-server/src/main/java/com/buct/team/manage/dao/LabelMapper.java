package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    List<Label> getAllLabelByUserId(@Param("userId") String userId);

    int deleteLabel(List<Long> idList);

    int addLabel(Label label);

    int updateLabel(Label label);
}
