package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/9 15:50
 */
@Mapper
@Repository
public interface MenuMapper {
    List<Menu> getMenuList();

    List<Menu> getMenuByRole(@Param("role") String role);
}
