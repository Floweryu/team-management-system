package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Assets;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/5/10 21:48
 */
@Mapper
@Repository
public interface AssetsMapper {
    List<Assets> getAllAssets();
    List<Assets> getAssetsByUploadUser(@Param("userId") String userId);

    List<Assets> getAssetsByUser(@Param("userId") String userId);

    int insertAssets(Assets assets);

    int updateAssets(Assets assets);

    int deleteAssets(List<Long> idList);
}
