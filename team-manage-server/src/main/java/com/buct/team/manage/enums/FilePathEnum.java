package com.buct.team.manage.enums;

/**
 * @author Floweryu
 * @date 2021/4/18 22:11
 */
public enum FilePathEnum {
    FILE_PATH("resource"),
    DOCUMENT("document"),
    CLASS("class"),
    IMG("img"),
    DOCUMENT_PATH(FILE_PATH,DOCUMENT),
    CLASS_PATH(FILE_PATH,CLASS),
    IMG_PATH(FILE_PATH, IMG);

    private final String path;

    FilePathEnum(String path) {
        this.path = path;
    }

    FilePathEnum(FilePathEnum firstPath, FilePathEnum secondPath) {
        this.path = "/" + firstPath.getPath() + "/" + secondPath.getPath() + "/";
    }

    public String getPath() {
        return this.path;
    }
}
