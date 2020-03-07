package com.poke.miaosha.redis;

/**
 * @ClassName OrderKey
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 9:34 下午
 */
public class OrderKey extends BasePrefix{
    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
