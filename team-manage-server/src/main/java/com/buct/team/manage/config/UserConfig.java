package com.buct.team.manage.config;

import com.buct.team.manage.enums.FilePathEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Floweryu
 * @date 2021/5/22 16:34
 */
@Configuration
public class UserConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(FilePathEnum.IMG_PATH.getPath() + "**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                        + FilePathEnum.FILE_PATH.getPath() + System.getProperty("file.separator")
                        + FilePathEnum.IMG.getPath() + System.getProperty("file.separator")
        );
    }
}
