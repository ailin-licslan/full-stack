package com.licslan.crudplay;

import com.licslan.crudplay.task.ConsumeTask;
import com.licslan.crudplay.utils.RedisInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.licslan.crudplay.utils.RedisInit.getRedis;

@SpringBootApplication
public class Boot {

	public static void main(String[] args) {

		SpringApplication.run(Boot.class, args);
		new ConsumeTask(getRedis()).taskRunning();
	}

	//1.下载安装 MySQL 8.0
	//2.登录 MySQL 服务查看
	//3.创建一张表
	//4.准备 data 1000w
	//5.Load数据到数据库 & 慢日志参数配置
	//6.条件查询数据 explain SELECT * FROM `licslan`.`data_test_licslan` WHERE `username` = '用户名_9999996\r';
	//7.创建索引查询数据并对比


	//磁盘IO整体时间大约9ms左右
	//磁盘寻道 5ms 旋转延迟 4.17ms 数据传输 0.xx ms
	//内存 100 纳秒

	//磁盘IO与内存耗时相差 9w倍 所以合适使用索引 可以大大减少磁盘IO次数

}
