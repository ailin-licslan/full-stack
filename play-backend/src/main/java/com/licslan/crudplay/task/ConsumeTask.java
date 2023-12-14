package com.licslan.crudplay.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * 延迟任务 定时任务
 *
 * 在Redis中，Sorted Set（有时称为ZSet，有序集合）是一种数据结构，它类似于Set，但每个成员都关联有一个分数（score），
 *
 * 用于排序。这使得Sorted Set不仅能够保持集合的唯一性，还能够根据分数进行有序排列。
 *
 * 底层实现Sorted Set的数据结构主要有两种：压缩列表（ziplist）和跳跃表（skiplist）。具体的选择取决于成员数量和大小。
 *
 * 压缩列表（ziplist）： 当Sorted Set的成员数量较小且每个成员的分数和值都较小时，Redis会使用压缩列表来实现。
 *
 * 压缩列表是一种紧凑的、特定格式的数组结构，能够有效地节省内存。
 *
 * 跳跃表（skiplist）： 当Sorted Set的成员数量较大或者成员的分数和值较大时，Redis会选择使用跳跃表。
 *
 * 跳跃表是一种有序数据结构，支持快速的查找、插入和删除操作。跳跃表的结构允许在不引入过多复杂性的情况下实现有序集合的高效操作。
 *
 * 对于跳跃表而言，每个节点包含一个成员和分数，同时，还包含多个层级的指针，这些指针允许快速地跳过一些节点，
 *
 * 从而加速搜索过程。跳跃表的节点按照分数从小到大的顺序排列，这样就实现了整个有序集合的有序性。
 *
 *
 * 跳表(skiplist、跳跃表) 是一个很优秀的数据结构，比如用于 Redis、levelDB等出名的开源项目上。跳表在原有的有序链表上面增加了多级索引，通过索引来实现快速查找
 * 跳表结合了链表和二分查找的思想
 * 由原始链表和一些通过“跳跃”生成的链表组成
 * 第0层是原始链表，越上层“跳跃”的越高，元素越少
 * 上层链表是下层链表的子序列
 * 查找时从顶层向下，不断缩小搜索范围
 *
 *
 * ziplist 是一个特殊双向链表，不像普通的链表使用前后指针关联在一起，它是存储在连续内存上的。
 * zlbytes: 32 位无符号整型，记录 ziplist 整个结构体的占用空间大小。当然了也包括 zlbytes本身。这个结构有个很大的用处，就是当需要修改 ziplist 时候不需要遍历即可知道其本身的大小。 这个 SDS中记录字符串的长度有相似之处，这些好的设计往往在平时的开发中可以采纳一下。
 * zltail: 32 位无符号整型, 记录整个 ziplist 中最后一个 entry 的偏移量。所以在尾部进行 POP操作时候不需要先遍历一次。
 * zllen: 16 位无符号整型, 记录 entry 的数量， 所以只能表示2^16。但是 Redis 作了特殊的处理：当实体数超过 2^16 ,该值被固定为 2^16 - 1。 所以这种时候要知道所有实体的数量就必须要遍历整个结构了。
 * entry: 真正存数据的结构。
 * zlend: 8 位无符号整型, 固定为 255 (0xFF)。为 ziplist 的结束标识。
 *
 * ziplist为了节省内存，采用了紧凑的连续存储。所以在修改操作下并不能像一般的链表那么容易，需要从新分配新的内存，然后复制到新的空间。
 * ziplist 是一个双向链表，可以在时间复杂度为 O(1) 从下头部、尾部进行 pop 或 push。
 * 新增或更新元素可能会出现连锁更新现象。
 * 不能保存过多的元素，否则查询效率就会降低。


 *
 *
 * 总体而言，Redis根据具体情况选择压缩列表或跳跃表作为Sorted Set的底层数据结构，以在不同场景下取得最佳性能。
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
