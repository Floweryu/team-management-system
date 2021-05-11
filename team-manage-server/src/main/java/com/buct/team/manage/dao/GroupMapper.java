package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/11 16:28
 */
@Repository
@Mapper
public interface GroupMapper {
    List<Group> getAllGroup();

    int insertGroup(Group group);

    int updateGroup(Group group);

    int deleteGroup(List<Long> idList);

    List<Group> selectByName(String name);

    Group getGroupById(@Param("id") Long id);
}
