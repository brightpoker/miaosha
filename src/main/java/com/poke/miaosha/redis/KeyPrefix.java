package com.poke.miaosha.redis;

/**
 * @InterfaceName Prefix
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 9:29 下午
 */
public interface KeyPrefix {
    int expireSeconds();

    String getPrefix();
}
