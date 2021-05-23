package com.buct.team.manage.service;

import com.buct.team.manage.entity.DocumentLabel;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:41
 */
public interface DocumentLabelService {
    List<Long> getLabelId(Long id);

    List<Long> getDocumentId(Long id);

    boolean addDocumentLabel(DocumentLabel documentLabel);
}
