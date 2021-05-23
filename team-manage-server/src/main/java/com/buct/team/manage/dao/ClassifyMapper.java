package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/1 23:21
 */
@Repository
@Mapper
public interface ClassifyMapper {
    List<Classify> getClassifyById(List<Long> id);

    List<Classify> getAllClassify();

    List<Classify> getAllClassifyByUserId(@Param("userId") String userId);

    int deleteClassify(List<Long> idList);

    int addClassify(Classify classify);

    int updateClassify(Classify classify);
}
