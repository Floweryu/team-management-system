package com.buct.team.manage.dao;

import com.buct.team.manage.entity.Document;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/4/17 15:42
 */
@Mapper
@Repository
public interface DocumentMapper {

    // 添加文献
    int insertDocument(Document document);

    // 更新文献
    int updatetDocument(Document document);

    // 获取文献
    List<Document> getAllDocument();

    List<String> findMd5IsExit(@Param("fileMd") String md5);
}
