package com.cz.order.controller;

import com.cz.util.RedisUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author vring
 * @ClassName OrderController.java
 * @Description
 * @createTime 2021/3/20 16:31
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {


    private RedisUtil redisUtil;

    @Autowired
    public OrderController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @PostMapping(value = "/add")
    public void  add(){
        String orderId = UUID.randomUUID().toString();
        log.info("接收到一笔待支付订单");
        redisUtil.ins("order_"+ orderId,orderId,30, TimeUnit.SECONDS);
    }
}
