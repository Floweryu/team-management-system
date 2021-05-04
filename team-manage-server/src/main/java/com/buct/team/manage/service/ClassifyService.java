package com.buct.team.manage.service;

import com.buct.team.manage.entity.Classify;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/1 23:34
 */
public interface ClassifyService {
    List<Classify> getClassifyById(List<Long> id);
}
