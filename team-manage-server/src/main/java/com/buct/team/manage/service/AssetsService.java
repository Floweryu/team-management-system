package com.buct.team.manage.service;

import com.buct.team.manage.controller.dto.AssetsReq;
import com.buct.team.manage.entity.Assets;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 22:17
 */
public interface AssetsService {

    List<Assets> getAllAssets();

    List<Assets> getAssetsByUploadUser(String userId);

    List<Assets> getAssetsByUser(String userId);

    boolean insertAssets(AssetsReq assetsReq);

    boolean updateAssets(AssetsReq assetsReq);

    boolean deleteAssets(List<Long> idList);
}
