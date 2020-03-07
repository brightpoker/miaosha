package com.poke.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName OrderInfo
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 6:38 下午
 */
@Data
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
