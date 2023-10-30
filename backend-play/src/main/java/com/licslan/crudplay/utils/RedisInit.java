package com.licslan.crudplay.utils;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class RedisInit {


    @Resource
    private RedisTemplate redisTemplate;

    private static RedisInit staticInstance = new RedisInit();

    @PostConstruct
    public void init(){
        staticInstance.redisTemplate = redisTemplate;
    }

    public static RedisTemplate getRedis(){
        return staticInstance.redisTemplate;
    }
}
