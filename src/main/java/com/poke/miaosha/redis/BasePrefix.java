package com.poke.miaosha.redis;

import lombok.AllArgsConstructor;

/**
 * @ClassName BasePrefix
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 9:30 下午
 */

@AllArgsConstructor
public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
