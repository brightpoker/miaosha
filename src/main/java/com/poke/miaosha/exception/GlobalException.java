package com.poke.miaosha.exception;

import com.poke.miaosha.result.CodeMsg;
import lombok.Getter;

/**
 * @ClassName GlobleException
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 2:37 上午
 */
@Getter
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }
}
