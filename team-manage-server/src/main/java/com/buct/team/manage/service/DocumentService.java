package com.buct.team.manage.service;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.entity.Document;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/17 17:17
 */
public interface DocumentService {
    boolean insertDocument(DocumentReq documentReq);

    List<Document> getAllDocument();

    boolean updateDocument(DocumentReq documentReq);

    List<String> findMd5IsExist(String md5);
}
