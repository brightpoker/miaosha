package com.poke.miaosha.redis;

/**
 * @ClassName MiaoshaKey
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/4 8:33 下午
 */
public class MiaoshaKey extends BasePrefix{
    private MiaoshaKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey(0, "go");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey(60, "mp");
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey(300, "vc");
}
