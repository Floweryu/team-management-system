package com.buct.team.manage.service;

import com.buct.team.manage.entity.Class;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 13:17
 */
public interface ClassService {
    boolean addClass(Class clas);

    List<Class> getClasses(String userId);

    boolean deleteClass(List<Long> idList);

    boolean updateClass(Class clas);
}
