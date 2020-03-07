package com.poke.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName MiaoshaGoods
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 6:36 下午
 */
@Data
public class MiaoshaGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
