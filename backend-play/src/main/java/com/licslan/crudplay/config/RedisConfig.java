package com.licslan.crudplay.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * 解释说明：
 * <p>
 * 当前配置类不是必须的，因为 Spring Boot 框架会自动装配 RedisTemplate 对象，但是默认的key序列化器为
 * <p>
 * JdkSerializationRedisSerializer，导致我们存到Redis中后的数据和原始数据有差别，故设置为
 * <p>
 * StringRedisSerializer序列化器。
 */


@Configuration
@Slf4j
public class RedisConfig {


    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory rcf) {

        log.info("创建redis模板对象~~~~");

        RedisTemplate redisTemplate = new RedisTemplate();

        //设置redis的连接工厂对象
        redisTemplate.setConnectionFactory(rcf);

        //设置redis key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //设置redis value的序列化器
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

}
