package com.poke.miaosha.rabbitmq;

import com.poke.miaosha.domain.MiaoshaUser;
import lombok.Data;

/**
 * @ClassName MiaoshaMessage
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/4 8:09 下午
 */
@Data
public class MiaoshaMessage {
    private MiaoshaUser user;
    private long goodsId;
}
