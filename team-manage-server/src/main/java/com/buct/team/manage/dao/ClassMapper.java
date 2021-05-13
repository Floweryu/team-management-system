package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 13:09
 */
@Repository
@Mapper
public interface ClassMapper {
    int addClass(Class clas);

    List<Class> getClasses(@Param("userId") String userId);

    int deleteClass(List<Long> idList);

    int updateClass(Class clas);
}
