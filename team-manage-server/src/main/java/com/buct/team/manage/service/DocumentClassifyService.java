package com.buct.team.manage.service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/1 23:01
 */
public interface DocumentClassifyService {
    List<Long> getClassifyId(Long id);

    List<Long> getDocumentId(Long id);
}
