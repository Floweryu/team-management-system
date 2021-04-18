package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.dao.DocumentMapper;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/17 17:18
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @Override
    public List<Document> getAllDocument() {
        return documentMapper.getAllDocument();
    }

    @Override
    public boolean insertDocument(DocumentReq documentReq) {
        Document document = new Document();
        return documentMapper.insertDocument(document) > 0;
    }

    @Override
    public boolean updateDocument(DocumentReq documentReq) {
        Document document = new Document();
        return documentMapper.updatetDocument(document) > 0;
    }
}
