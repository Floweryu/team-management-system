package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.SoftwareReq;
import com.buct.team.manage.dao.SoftwareMapper;
import com.buct.team.manage.entity.Software;
import com.buct.team.manage.service.SoftwareService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/8 19:59
 */
@Service
public class SoftwareServiceImpl implements SoftwareService {
    final SoftwareMapper softwareMapper;

    public SoftwareServiceImpl(SoftwareMapper softwareMapper) {
        this.softwareMapper = softwareMapper;
    }

    @Override
    public List<Software> getAllSoftware() {
        return softwareMapper.getAllSoftware();
    }

    @Override
    public boolean insertSoftware(SoftwareReq softwareReq) {
        Software software = transSoftware(softwareReq);
        return softwareMapper.insertSoftware(software) > 0;
    }

    @Override
    public List<Software> getAllSoftwareByUserId(String userId) {
        return softwareMapper.getAllSoftwareByUserId(userId);
    }

    @Override
    public boolean updateSoftware(SoftwareReq softwareReq) {
        Software software = transSoftware(softwareReq);
        return softwareMapper.updateSoftware(software) > 0;
    }

    @Override
    public boolean deleteSoftware(List<Long> idList) {
        return softwareMapper.deleteSoftware(idList) > 0;
    }

    @Override
    public List<Software> searchByName(String name) {
        return softwareMapper.searchByName(name);
    }

    private Software transSoftware(SoftwareReq softwareReq) {
        Long id = softwareReq.getId();
        String name = softwareReq.getName();
        String version = softwareReq.getVersion();
        String downloadLink = softwareReq.getDownloadLink();
        String remark = softwareReq.getRemark();

        Software software = new Software();
        if (id != null) {
            software.setId(id);
        }
        if (name != null) {
            software.setName(name);
        }
        if (version != null) {
            software.setVersion(version);
        }
        if (downloadLink != null) {
            software.setDownloadLink(downloadLink);
        }
        if (remark != null) {
            software.setRemark(remark);
        }
        return software;
    }
}
