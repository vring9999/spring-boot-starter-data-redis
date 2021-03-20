package com.cz.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title
 * @description 订单过期监听：接收过期的redis消息,获取到key（订单号）然后去更新对应订单状态
 * @author vring
 * @param:
 * @throws
 */
@Transactional
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //  获取失效的key
        String expiredKey = message.toString();
        String prefix = "";
        if(expiredKey.indexOf("_") != -1){
            prefix = expiredKey.substring(0,expiredKey.indexOf("_"));
        }
        //根据key的前缀判断是不是属于订单过期
        if("order".equals(prefix)){
            String orderId = expiredKey.substring(expiredKey.indexOf("_") + 1);
            log.info("订单编号：{}已过期，开始处理==========",orderId);

        }
    }
}

