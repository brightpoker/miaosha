package com.poke.miaosha.rabbitmq;

import com.poke.miaosha.domain.MiaoshaOrder;
import com.poke.miaosha.domain.MiaoshaUser;
import com.poke.miaosha.redis.RedisService;
import com.poke.miaosha.result.CodeMsg;
import com.poke.miaosha.result.Result;
import com.poke.miaosha.service.GoodsService;
import com.poke.miaosha.service.MiaoshaService;
import com.poke.miaosha.service.OrderService;
import com.poke.miaosha.vo.GoodsVo;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MQReceiver
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/4 2:48 上午
 */
@Service
@Log4j
public class MQReceiver {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

//    @RabbitListener(queues = MQConfig.QUEUE)
//    public void receive(String message) {
//        log.info("receive message:" + message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1(String message) {
//        log.info("topic queue1 message:" + message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
//    public void receiveTopic2(String message) {
//        log.info("topic queue2 message:" + message);
//    }

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user= mm.getUser();
        long goodsId = mm.getGoodsId();

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return;
        }

        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return;
        }

        miaoshaService.miaosha(user, goods);

    }
}
