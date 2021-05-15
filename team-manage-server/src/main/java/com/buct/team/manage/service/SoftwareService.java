package com.buct.team.manage.service;

import com.buct.team.manage.controller.dto.SoftwareReq;
import com.buct.team.manage.entity.Software;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/8 19:59
 */
public interface SoftwareService {
    List<Software> getAllSoftware();

    List<Software> getAllSoftwareByUserId(String userId);

    List<Software> searchByName(String name);

    boolean insertSoftware(SoftwareReq softwareReq);

    boolean updateSoftware(SoftwareReq softwareReq);

    boolean deleteSoftware(List<Long> idList);
}
