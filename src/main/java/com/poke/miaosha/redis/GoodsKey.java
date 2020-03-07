package com.poke.miaosha.redis;

/**
 * @ClassName UserKey
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 9:34 下午
 */
public class GoodsKey extends BasePrefix{
    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
    public static GoodsKey getMiaoshaGoodsStock = new GoodsKey(0, "gs");
}
