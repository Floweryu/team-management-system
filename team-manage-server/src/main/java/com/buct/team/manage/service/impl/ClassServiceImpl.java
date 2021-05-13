package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.ClassMapper;
import com.buct.team.manage.entity.Class;
import com.buct.team.manage.service.ClassService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/13 13:18
 */
@Service
public class ClassServiceImpl implements ClassService {
    final ClassMapper classMapper;

    public ClassServiceImpl(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    @Override
    public boolean addClass(Class clas) {
        return classMapper.addClass(clas) > 0;
    }

    @Override
    public List<Class> getClasses(String userId) {
        return classMapper.getClasses(userId);
    }

    @Override
    public boolean deleteClass(List<Long> idList) {
        return classMapper.deleteClass(idList) > 0;
    }

    @Override
    public boolean updateClass(Class clas) {
        return classMapper.updateClass(clas) > 0;
    }
}
