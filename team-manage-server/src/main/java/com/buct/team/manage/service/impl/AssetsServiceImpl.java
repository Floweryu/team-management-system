package com.buct.team.manage.service.impl;

import com.buct.team.manage.controller.dto.AssetsReq;
import com.buct.team.manage.dao.AssetsMapper;
import com.buct.team.manage.entity.Assets;
import com.buct.team.manage.service.AssetsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 22:18
 */
@Service
public class AssetsServiceImpl implements AssetsService {
    final AssetsMapper assetsMapper;

    public AssetsServiceImpl(AssetsMapper assetsMapper) {
        this.assetsMapper = assetsMapper;
    }

    @Override
    public List<Assets> getAllAssets() {
        return assetsMapper.getAllAssets();
    }

    @Override
    public List<Assets> getAssetsByUploadUser(String userId) {
        return assetsMapper.getAssetsByUploadUser(userId);
    }

    @Override
    public List<Assets> getAssetsByUser( String userId){
        return assetsMapper.getAssetsByUser(userId);
    }

    @Override
    public boolean insertAssets(AssetsReq assetsReq) {
        Assets assets = transAssets(assetsReq);
        return assetsMapper.insertAssets(assets) > 0;
    }

    @Override
    public boolean updateAssets(AssetsReq assetsReq) {
        Assets assets = transAssets(assetsReq);
        return assetsMapper.updateAssets(assets) > 0;
    }

    @Override
    public boolean deleteAssets(List<Long> idList) {
        return assetsMapper.deleteAssets(idList) > 0;
    }

    private Assets transAssets(AssetsReq assetsReq) {
        Long id = assetsReq.getId();
        String number = assetsReq.getNumber();
        String name = assetsReq.getName();
        String userId = assetsReq.getUserId();
        String remark = assetsReq.getRemark();
        String byUserId = assetsReq.getByUserId();

        Assets assets = new Assets();
        if (id != null) {
            assets.setId(id);
        }
        if (number != null) {
            assets.setNumber(number);
        }
        if (name != null) {
            assets.setName(name);
        }
        if (userId != null) {
            assets.setUserId(userId);
        }
        if (remark != null) {
            assets.setRemark(remark);
        }
        if (byUserId != null) {
            assets.setByUserId(byUserId);
        }
        return assets;
    }
}
