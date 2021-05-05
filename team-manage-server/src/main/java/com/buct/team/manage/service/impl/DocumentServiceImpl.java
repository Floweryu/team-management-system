package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.DocumentReq;
import com.buct.team.manage.dao.DocumentMapper;
import com.buct.team.manage.entity.Document;
import com.buct.team.manage.service.DocumentService;
import org.springframework.stereotype.Service;
import java.util.Date;
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

    @Override
    public boolean deleteDocument(List<Long> idList) {
        return documentMapper.deleteDocument(idList) > 0;
    }

    @Override
    public List<Document> selectByTitle(String title) {
        return documentMapper.selectByTitle(title);
    }

    @Override
    public List<Document> selectByAuthor(String author) {
        return documentMapper.selectByAuthor(author);
    }

    @Override
    public List<Document> selectByPublishPlace(String publishPlace) {
        return documentMapper.selectByPublishPlace(publishPlace);
    }

    @Override
    public Document selectById(Long id) {
        return documentMapper.selectById(id);
    }

    @Override
    public boolean updateAverageScore(Long id, Double score) {
        return documentMapper.updateAverageScore(id, score) > 0;
    }

    @Override
    public Long getLikeCount(Long id) {
        return documentMapper.getLikeCount(id);
    }

    private Document transDocument(DocumentReq documentReq) {

        Document document = new Document();

        Long id = documentReq.getId();
        String title = documentReq.getTitle();
        String author = documentReq.getAuthor();
        Date publishTime = documentReq.getPublishTime();
        String introduction = documentReq.getIntroduction();
        String publishPlace = documentReq.getPublishPlace();
        Double size = documentReq.getSize();
        String uploadUserId = documentReq.getUploadUserId();
        String originAddress = documentReq.getOriginAddress();
        String storePath = documentReq.getStorePath();
        String fileName = documentReq.getFileName();
        Long likeCount = documentReq.getLikeCount();
        Double averageScore = documentReq.getAverageScore();
        Long viewCount = documentReq.getViewCount();
        Long downloadCount = documentReq.getDownloadCount();
        if (id != null) {
            document.setId(id);
        }
        if (title != null) {
            document.setTitle(title.trim());
        }
        if (author != null) {
            document.setAuthor(author);
        }
        if (publishTime != null) {
            document.setPublishTime(publishTime);
        }
        if (introduction != null) {
            document.setIntroduction(introduction.trim());
        }
        if (publishPlace != null) {
            document.setPublishPlace(publishPlace.trim());
        }
        if (size != null) {
            document.setSize(size);
        }
        if (uploadUserId != null) {
            document.setUploadUserId(uploadUserId);
        }
        if (originAddress != null) {
            document.setOriginAddress(originAddress);
        }
        if (storePath != null) {
            document.setStorePath(storePath);
        }
        if (fileName != null) {
            document.setFileName(fileName);
        }
        if (likeCount != null) {
            document.setLikeCount(likeCount);
        }
        if (averageScore != null) {
            document.setAverageScore(averageScore);
        }

        if (viewCount != null) {
            document.setViewCount(viewCount);
        }
        if (downloadCount != null) {
            document.setDownloadCount(downloadCount);
        }
        return document;
    }
}
