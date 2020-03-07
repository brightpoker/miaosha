package com.poke.miaosha.service;

import com.poke.miaosha.dao.GoodsDao;
import com.poke.miaosha.domain.Goods;
import com.poke.miaosha.domain.MiaoshaGoods;
import com.poke.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 6:49 下午
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        return goodsDao.reduceStock(g) > 0;
    }
}
