package com.buct.team.manage.config;

import com.buct.team.manage.enums.FilePathEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Floweryu
 * @date 2021/4/18 20:03
 */
@Configuration
public class DocumentConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(FilePathEnum.DOCUMENT_PATH.getPath() + "**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                        + FilePathEnum.FILE_PATH.getPath() + System.getProperty("file.separator")
                        + FilePathEnum.DOCUMENT.getPath() + System.getProperty("file.separator")
        );
    }
}
