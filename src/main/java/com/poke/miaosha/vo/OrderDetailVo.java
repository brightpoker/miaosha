package com.poke.miaosha.vo;

import com.poke.miaosha.domain.OrderInfo;
import lombok.Data;

/**
 * @ClassName OrderDetailVo
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/3 1:10 上午
 */
@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}
