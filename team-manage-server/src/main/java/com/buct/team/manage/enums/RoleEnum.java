package com.buct.team.manage.enums;

/**
 * @author Floweryu
 * @date 2021/5/15 19:56
 */
public enum RoleEnum {
    ADMIN("admin"),
    COMMON("common");
    private final String state;

    RoleEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
