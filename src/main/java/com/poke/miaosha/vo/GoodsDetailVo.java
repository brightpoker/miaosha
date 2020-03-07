package com.poke.miaosha.vo;

import com.poke.miaosha.domain.MiaoshaUser;
import lombok.Data;

/**
 * @ClassName GoodsDetailVo
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/2 11:34 下午
 */
@Data
public class GoodsDetailVo {
    private GoodsVo goods;
    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private MiaoshaUser user;
}
