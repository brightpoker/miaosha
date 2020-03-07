package com.poke.miaosha.vo;

import com.poke.miaosha.domain.Goods;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName GoodsVo
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 6:50 下午
 */
@Data
public class GoodsVo extends Goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
