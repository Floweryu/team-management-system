package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.ClassifyMapper;
import com.buct.team.manage.entity.Classify;
import com.buct.team.manage.service.ClassifyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/1 23:35
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {
    private  final ClassifyMapper classifyMapper;

    public ClassifyServiceImpl(ClassifyMapper classifyMapper) {
        this.classifyMapper = classifyMapper;
    }

    @Override
    public List<Classify> getClassifyById(List<Long> id) {
        return classifyMapper.getClassifyById(id);
    }
}
