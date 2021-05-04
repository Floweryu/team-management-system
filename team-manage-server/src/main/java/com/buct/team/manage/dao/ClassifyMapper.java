package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Classify;
import org.apache.ibatis.annotations.Mapper;
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
}
