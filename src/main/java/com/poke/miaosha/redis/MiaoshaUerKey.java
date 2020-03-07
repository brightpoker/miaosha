package com.poke.miaosha.redis;

/**
 * @ClassName MiaoshaUerKey
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 3:09 上午
 */
public class MiaoshaUerKey extends BasePrefix{
    public static final int TOKEN_EXPIRE = 3600 * 24 *2;

    public MiaoshaUerKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaUerKey token = new MiaoshaUerKey(TOKEN_EXPIRE, "tk");
    public static MiaoshaUerKey getById = new MiaoshaUerKey(0, "id");


}
