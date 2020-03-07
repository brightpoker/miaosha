package com.poke.miaosha.result;

import lombok.Getter;

/**
 * @ClassName result
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 5:56 下午
 */
@Getter
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
     * @Description 成功时候调用
     *
     * @param
     * @return com.poke.miaosha.result.Result<T>
     **/
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    /**
     * @Description 失败时候调用
     *
     * @param cm
     * @return com.poke.miaosha.result.Result<T>
     **/
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<>(cm);
    }
}
