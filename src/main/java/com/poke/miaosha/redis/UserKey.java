package com.poke.miaosha.redis;

/**
 * @ClassName UserKey
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 9:34 下午
 */
public class UserKey extends BasePrefix{
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
