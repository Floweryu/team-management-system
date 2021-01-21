package com.buct.team.manage.result;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/1/21 19:55
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功时构造函数
     * @param data  成功返回的数据
     */
    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    /**
     * 成功时无数据返回构造函数
     * @param code  状态码
     * @param msg   返回信息
     */
    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 自定义返回信息
     * @param code  状态码
     * @param msg   信息
     * @param data  数据
     */
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 根据codeMsg构造函数
     * @param codeMsg   状态码和错误信息
     */
    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * 成功时返回
     * @param data 自定义 data
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    /**
     * 成功时返回
     * @param code 自定义 code
     * @param msg  自定义 msg
     */
    public static <T> Result<T> success(int code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * 成功时返回
     * @param code 自定义 code
     * @param msg  自定义 msg
     * @param data 自定义 data
     */
    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    /**
     * 全局成功信息
     * @param codeMsg 全局消息
     */
    public static <T> Result<T> success(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 全局错误信息
     * @param codeMsg   状态码和错误信息
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 自定义错误 code, msg
     * @param code 自定义code
     * @param msg  自定义msg
     */
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg);
    }
}
