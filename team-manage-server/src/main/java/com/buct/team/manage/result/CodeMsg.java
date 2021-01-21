package com.buct.team.manage.result;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/1/21 19:51
 */
@Data
public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg FAILURE = new CodeMsg(-1, "there is something error");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500, "Server exception");

    private CodeMsg(int code, String codeMsg) {
        this.code = code;
        this.msg = codeMsg;
    }
}
