package com.buct.team.manage.service;

import com.buct.team.manage.entity.Label;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:40
 */
public interface LabelService {
    List<Label> getLabelById(List<Long> id);

    List<Label> getAllLabel();

    List<Label> getAllLabelByUserId(String userId);

    boolean deleteLabel(List<Long> idList);

    boolean addLabel(Label label);

    boolean updateLabel(Label label);
}
