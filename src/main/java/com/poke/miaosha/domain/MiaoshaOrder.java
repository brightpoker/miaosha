package com.poke.miaosha.domain;

import lombok.Data;

/**
 * @ClassName MiaoshaOrder
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 6:47 下午
 */
@Data
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
