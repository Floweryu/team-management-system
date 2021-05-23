package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.DocumentClassifyMapper;
import com.buct.team.manage.entity.DocumentClassify;
import com.buct.team.manage.service.DocumentClassifyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/1 23:02
 */
@Service
public class DocumentClassifyServiceImpl implements DocumentClassifyService {

    private final DocumentClassifyMapper documentClassifyMapper;

    public DocumentClassifyServiceImpl(DocumentClassifyMapper documentClassifyMapper) {
        this.documentClassifyMapper = documentClassifyMapper;
    }

    @Override
    public boolean addDocumentClassify(DocumentClassify documentClassify) {
        return documentClassifyMapper.addDocumentClassify(documentClassify) > 0;
    }

    @Override
    public List<Long> getClassifyId(Long id) {
        return documentClassifyMapper.getClassifyId(id);
    }

    @Override
    public List<Long> getDocumentId(Long id) {
        return documentClassifyMapper.getDocumentId(id);
    }
}
