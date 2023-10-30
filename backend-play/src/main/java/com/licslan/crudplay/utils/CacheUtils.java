package com.licslan.crudplay.utils;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/***
 * @author LICSLAN
 * 本地缓存工具
 */

@Slf4j
public class CacheUtils {

    /**
     * 缓存默认失效时间(毫秒) 一个小时
     */
    private static final long DEFAULT_TIMEOUT = 3600 * 1000;

    /**
     * 缓存清除动作执行间隔(秒)  2s 去清理一下 这个可以按照实际情况自己定义
     */
    private static final long CLEAN_UP_TIME_GAP = 2;

    /**
     * 缓存存储的map
     */
    private static final ConcurrentHashMap<String, CacheEntity> cacheMap = new ConcurrentHashMap<>(16);


    public CacheUtils() {
    }

    private static CacheUtils cache = null;

    public static CacheUtils getInstance() {

        //单例一下
        if (cache == null) {
            cache = new CacheUtils();
            new Thread(new TimeoutTimer()).start();
        }

        return cache;
    }

    /**
     * 定时器线程  每2s 检查缓存过期
     */
    static class TimeoutTimer implements Runnable {

        @Override
        public void run() {

            while (true) {

                try {

                    //每隔 2 s 检查一次
                    TimeUnit.SECONDS.sleep(CLEAN_UP_TIME_GAP);

                    //遍历缓存
                    for (String key : cacheMap.keySet()) {

                        CacheEntity entity = cacheMap.get(key);

                        long now = System.currentTimeMillis();

                        //当前时间 - 当时创建这个缓存的时间戳  大于等于 过期设置的时间
                        if ((now - entity.getTimeStamp()) >= entity.getExpire()) {

                            //就移除缓存的内容
                            cacheMap.remove(key);
                            log.info("移除的缓存的 key 是： {}", key);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 存储单元
     */
    @Data
    static class CacheEntity {

        /**
         * 值
         */
        private Object value;

        /**
         * 过期时间(毫秒)
         */
        private long expire;

        /**
         * 创建时的时间戳
         */
        private long timeStamp;

    }


    /**
     * 设置指定过期时间的缓存
     */
    public static boolean set(String key, Object value, long expire) {

        cacheMap.put(key, setEntity(key, value, expire));

        return true;
    }


    /**
     * 设置默认过期时间的缓存
     */
    public static boolean set(String key, Object value) {

        cacheMap.put(key, setEntity(key, value, DEFAULT_TIMEOUT));

        return true;
    }

    /**
     * 设置保存缓存的内容 过期时间
     */
    private static CacheEntity setEntity(String key, Object value, long expire) {

        log.info("设置的缓存的 key 是： {}", key);

        CacheEntity entity = new CacheEntity();
        entity.setValue(value);
        entity.setExpire(expire);
        entity.setTimeStamp(System.currentTimeMillis());

        return entity;
    }


    /**
     * 获取value
     */
    public static Object get(String key) {
        CacheEntity entity = cacheMap.get(key);

        if (entity == null) {
            return null;
        } else {
            return entity.getValue();
        }
    }


    /**
     * 移除缓存内容
     */
    public static void remove(String key) {
        cacheMap.remove(key);
    }


    /**
     * 测试案例  main2 --> main 放开测试即可
     */
    @SneakyThrows
    public static void main2(String[] args) {

        String key = "key";
        String value = "value";

        CacheUtils cache = CacheUtils.getInstance();
        cache.set(key, value, 50002);
        Object result = cache.get(key);
        System.out.println(result.toString());

        Thread.sleep(5000);
        Object result2 = cache.get(key);
        System.out.println(result2.toString());

    }

}
