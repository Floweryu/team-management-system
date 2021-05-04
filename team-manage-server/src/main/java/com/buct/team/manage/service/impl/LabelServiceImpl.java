package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.LabelMapper;
import com.buct.team.manage.entity.Label;
import com.buct.team.manage.service.LabelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:42
 */
@Service
public class LabelServiceImpl implements LabelService {

    private final LabelMapper labelMapper;

    public LabelServiceImpl(LabelMapper labelMapper) {
        this.labelMapper = labelMapper;
    }

    @Override
    public List<Label> getLabelById(List<Long> id) {
        return labelMapper.getLabelById(id);
    }
}
