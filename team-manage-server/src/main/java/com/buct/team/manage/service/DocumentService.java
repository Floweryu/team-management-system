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

    boolean deleteDocument(List<Long> idList);

    List<Document> selectByTitle(String title);

    List<Document> selectByAuthor(String author);

    List<Document> selectByPublishPlace(String publishPlace);

    Document selectById(Long id);

    boolean updateAverageScore(Long id, Double score);

    Long getLikeCount(Long id);

    Long getDownloadCount(Long id);

    List<Document> getDocumentById(List<Long> idList);

    List<Document> getAllDocumentByUploadUser(String userId);
}
