package com.licslan.crudplay.task;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
/**
 * 延迟任务 定时任务
 */
@Slf4j
@Service
public class ConsumeTask {
    private static final String REDIS_KEY_POST = "task:happen:time";
    private final RedisTemplate redisTemplate;
    private ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();

    public ConsumeTask(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * task: This design must have practical business meaning. It can also be a combination of json
     * string + timestamp. timestamp indicates the time when the task is to be executed regularly.
     */
    public void produce(String v, Long timestamp) {
        log.info("BEGIN ADD CONTENT :{} TO REDIS, THE TIMESTAMP IS :{}", v, timestamp);
        Boolean add = addTask(v, timestamp);
        log.info("ADD TASK TO REDIS IS {}", add);
    }

    private Boolean addTask(String v, Long timestamp) {
        ZSetOperations zso = redisTemplate.opsForZSet();
        Boolean add= zso.add(REDIS_KEY_POST, v, timestamp);
        check(zso, add);
        return add;
    }

    private static void check(ZSetOperations zso, Boolean add) {
        if (Boolean.TRUE.equals(add)) {
            Long size = zso.size(REDIS_KEY_POST);
            if (size != null) {
                Set set = zso.rangeWithScores(REDIS_KEY_POST, 0L, size);
                if (set != null) {
                    set.forEach(tuple -> log.info("member：{} score：{}", tuple));
                }
            }
        }
    }


    /**
     * Executes with a fixed delay, which is the delay between the termination of one execution and
     * the start of the next.
     */
    public void taskRunning() {
        ex.scheduleWithFixedDelay(new Task(), 1, 50, TimeUnit.SECONDS);
    }

    public class Task implements Runnable {
        @Override
        public void run() {

            log.info("任务每隔几秒执行一次 : "+ System.currentTimeMillis());


            long time = System.currentTimeMillis();

            ZSetOperations zso = redisTemplate.opsForZSet();
            //Take out overdue tasks and take 30 items at a time. adjust them appropriately here.



            Set objects = zso.rangeByScore(REDIS_KEY_POST, 0, time, 0, 30);

            if (objects == null || objects.isEmpty()) {
                return;
            }

            for (Object task : objects) {

                //And delete the corresponding data key + id in redis
                Long count = zso.remove(REDIS_KEY_POST, task);
                log.info("HANDLE LOGIC ..., VO IS {}", task);
                if (count != null && count == 1) {
                    try {
                        //The real business logic processing is to send a request
                        log.info("HANDLE LOGIC ..., VO IS {}", task);
                    } catch (Exception e) {
                        log.error("SENDPOST HAVE ERROR PLS CHECK: {}, SEND CONTENT:{}", e, task);
                    } finally {
                        log.info("RECORD LOG HERE PLZ ... SEND CONTENT:{}", task);
                    }
                }
            }
        }
    }

}
