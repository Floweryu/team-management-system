package com.buct.team.manage.service;

import com.buct.team.manage.entity.FileMd;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/21 15:30
 */
public interface FileMdService {
    List<String> getFilePathByMd(String md5);

    boolean insertFilePathMd(FileMd fileMd);
}
