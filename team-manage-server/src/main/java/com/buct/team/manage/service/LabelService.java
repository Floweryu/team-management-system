package com.buct.team.manage.service;

import com.buct.team.manage.entity.Label;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:40
 */
public interface LabelService {
    List<Label> getLabelById(List<Long> id);
}
