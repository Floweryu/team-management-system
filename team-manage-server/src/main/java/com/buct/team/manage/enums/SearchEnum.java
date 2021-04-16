package com.buct.team.manage.enums;

/**
 * @author Floweryu
 * @date 2021/4/16 17:16
 */
public enum SearchEnum {
    /**
     * USER_ID: 用户账号; USERNAME: 用户姓名; IDENTITY: 用户身份
     */
    USER_ID(0),
    USERNAME(1),
    IDENTITY(2);

    private final Integer state;

    SearchEnum(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
