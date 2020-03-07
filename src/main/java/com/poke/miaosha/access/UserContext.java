package com.poke.miaosha.access;

import com.poke.miaosha.domain.MiaoshaUser;

/**
 * @ClassName UserContext
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/5 12:52 上午
 */
public class UserContext {
    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<>();

    public static void setUser(MiaoshaUser user) {
        userHolder.set(user);
    }

    public static MiaoshaUser getUser() {
        return userHolder.get();
    }
}
