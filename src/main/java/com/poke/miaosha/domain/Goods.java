package com.poke.miaosha.domain;

import lombok.Data;

/**
 * @ClassName Goods
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 6:34 下午
 */
@Data
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
}
