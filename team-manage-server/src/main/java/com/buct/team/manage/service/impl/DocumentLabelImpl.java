package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.DocumentLabelMapper;
import com.buct.team.manage.service.DocumentLabelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/2 14:45
 */
@Service
public class DocumentLabelImpl implements DocumentLabelService {

    final DocumentLabelMapper documentLabelMapper;

    public DocumentLabelImpl(DocumentLabelMapper documentLabelMapper) {
        this.documentLabelMapper = documentLabelMapper;
    }

    @Override
    public List<Long> getLabelId(Long id) {
        return documentLabelMapper.getLabelId(id);
    }
}
