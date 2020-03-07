package com.poke.miaosha.rabbitmq;

import com.poke.miaosha.redis.RedisService;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MQSender
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/4 2:48 上午
 */
@Service
@Log4j
public class MQSender {
    @Autowired
    AmqpTemplate amqpTemplate;

//    public void send(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send message:" + msg);
//        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
//    }
//
//    public void sendTopic(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send topic message:" + msg);
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg + 1);
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg + 2);
//    }
//
//    public void sendFanout(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send fanout message:" + msg);
//        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "",msg);
//    }

    public void sendMiaoshaMessage(MiaoshaMessage message) {
        String msg = RedisService.beanToString(message);
        log.info("send miaosha message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
    }
}
