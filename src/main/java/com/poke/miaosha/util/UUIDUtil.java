package com.poke.miaosha.util;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 3:07 上午
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
