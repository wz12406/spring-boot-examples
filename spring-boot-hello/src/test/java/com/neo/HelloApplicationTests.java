package com.neo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HelloApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("Hello Spring Boot 2.0!");
	}


	@Test
	public void test_001() {
		HashMap map = new HashMap();
		map.put("123","123");

		ConcurrentHashMap map2 = new ConcurrentHashMap();
		map2.put("aaa","123");

	}


	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	JedisPool jedisPool;
	/**
	 * 使用redis 列表实现生产者效费者模式
	 * 一个线程生产数据  1秒消费一个
	 * 4个线程消费数据  每两秒消费一个
	 *效果： 避免出现并发消费
	 *
	 */
	@Test
	public void testRedis() throws InterruptedException {


		for(int i = 0;i<2;i++){
			new Thread(()->{
				int count = 0;
				Jedis jedis = jedisPool.getResource();
				while (true){
					jedis.rpush("TestBlockQueue","task"+count++);
					System.out.println("生产线程生产任务：task"+count);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			},"生产者线程").start();
		}

		int millis = 1000000000;
		Thread.sleep(millis);
	}

	@Test
	public void testRedisCustomer() throws InterruptedException {
		String[] listKey = {"TestBlockQueue"};
		for(int i = 0;i<4;i++){
			new Thread(()->{
				while (true){
					Jedis jedis = jedisPool.getResource();
					List<String> blpop = jedis.blpop(0,listKey);
					System.out.println("消费者消费消息："+blpop.get(1));
					jedis.close();
				}
			},"消费者线程"+i).start();
		}

		TimeUnit.SECONDS.sleep(1000000);
	}


	/**
	 * 测试map遍历
	 * @throws InterruptedException
	 */
	@Test
	public void test_005() throws InterruptedException {
		Map map = new HashMap();
		map.put("aaa",123);
		map.put("ccc",123);
		map.put("bbb",123);

		Set<Map.Entry> set = map.entrySet();
		set.stream().forEach(entry -> {
			System.out.println(entry.getKey()+"---"+entry.getValue());
		});

		TimeUnit.SECONDS.sleep(1000000);
	}

}
