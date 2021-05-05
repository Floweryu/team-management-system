package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Floweryu
 * @date 2021/5/5 20:13
 */
@Repository
@Mapper
public interface LikeMapper {
    int checkDuplicateLike(@Param("documentId") Long documentId, @Param("userId") String userId);

    int addLike(Like like);

    int deleteLike(Like like);
}
