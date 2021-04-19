package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.dao.DocumentMapper;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.result.CodeMsg;
import com.buct.team.manage.result.Result;
import com.buct.team.manage.service.DocumentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.Date;
import java.sql.Timestamp;
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
        Document document = transDocument(documentReq);
        return documentMapper.insertDocument(document) > 0;
    }

    @Override
    public boolean updateDocument(DocumentReq documentReq) {
        Document document = transDocument(documentReq);
        return documentMapper.updatetDocument(document) > 0;
    }

    private Document transDocument(DocumentReq documentReq) {

        Document document = new Document();
        document.setId(documentReq.getId());
        document.setTitle(documentReq.getTitle().trim());
        document.setAuthor(documentReq.getAuthor().trim());
        document.setPubishTime(documentReq.getPubishTime());
        document.setIntroduction(documentReq.getIntroduction().trim());
        document.setPublishPlace(documentReq.getPublishPlace().trim());
        document.setSize(documentReq.getSize());
        document.setUploadUserId(documentReq.getUploadUserId());
        document.setOriginAddress(document.getOriginAddress());
        document.setStorePath(document.getStorePath());

        return document;
    }
}
