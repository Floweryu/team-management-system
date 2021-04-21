package com.buct.team.manage.service.impl;

import com.buct.team.manage.dao.FileMdMapper;
import com.buct.team.manage.entity.FileMd;
import com.buct.team.manage.service.FileMdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/21 15:31
 */
@Service
public class FileMdServiceImpl implements FileMdService {

    private final FileMdMapper fileMdMapper;

    public FileMdServiceImpl(FileMdMapper fileMdMapper) {
        this.fileMdMapper = fileMdMapper;
    }

    @Override
    public List<String> getFilePathByMd(String md5) {
        return fileMdMapper.getFilePathByMd(md5);
    }

    @Override
    public boolean insertFilePathMd(FileMd fileMd) {
        return fileMdMapper.insertFilePathMd(fileMd) > 0;
    }
}
