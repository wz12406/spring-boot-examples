package com.neo.np2p;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Np2pReactorCustomer {

	@Autowired
	JedisPool jedisPool;

	ExecutorService executor = Executors.newFixedThreadPool(200 + 6);

	@Test
	public void testCustomer08() throws InterruptedException {
		testCustomer();
	}

	@Test
	public void testCustomer07() throws InterruptedException {
		testCustomer();
	}

	@Test
	public void testCustomer06() throws InterruptedException {
		testCustomer();
	}

	@Test
	public void testCustomer05() throws InterruptedException {
		testCustomer();
	}

	@Test
	public void testCustomer04() throws InterruptedException {
		testCustomer();
	}

	@Test
	public void testCustomer03() throws InterruptedException {
		testCustomer();
	}

	@Test
	public void testCustomer02() throws InterruptedException {
		testCustomer();
	}

	/**
	 * 使用redis 列表实现生产者效费者模式
	 * 一个线程生产数据  1秒消费一个
	 * 4个线程消费数据  每两秒消费一个
	 *效果： 避免出现并发消费
	 */
	@Test
	public void testCustomer() throws InterruptedException {
		Jedis jedis = jedisPool.getResource();
		String[] keys = {"test_s_t_key1","test_s_t_key2","test_s_t_key3","test_s_t_key4"};
		//任务池任务最大阀值
		long maxCnt = 5000;
		//任务池任务最小阀值
		long minCnt = 500;

		long start = System.currentTimeMillis();
		for (;;) {

			/** 获取任务 */
			try {
				// 获取对应任务
				List<String> list = getTaskList(jedis,keys);

				if(list.size()==1||list.size()==0){
					long end = System.currentTimeMillis();
					log.info("消息消费完毕，总计耗时："+(end-start)+"ms");
					break;
				}
				for (final String task : list) {

					executor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(RandomUtil.randomInt(100,200));
								log.info("消息消费完毕："+task);
							} catch (Exception e) {
								log.error("executor.execute.run()", e);
							}
						}
					});
				}
			} catch (Exception e) {
				log.error("AbstractDispather.run()", e);
			}

			/** 等待队列消耗完后进行下次任务获取 */
			try {

				long size = getQueueSize();

				log.info("当前任务队列大小："+size);
				if (size > maxCnt) {

					while (size > minCnt) {

						log.info("任务消费当前队列大小：" + size);
						// 线程休眠时间
						long sleepTime = 1000;
						Thread.sleep(sleepTime);
						size = getQueueSize();

					}
				}
			} catch (Exception e) {

				log.error("AbstractDispather.run()", e);
			}
		}
		
	}

	private long getQueueSize() {
		return ((ThreadPoolExecutor) executor).getQueue().size();
	}

	private List<String> getTaskList(Jedis jedis,String[] keys) throws Exception{

		/** 从消息队列获取任务ID */
		List<String> dataList = jedis.blpop(1,keys);


		if (dataList != null && dataList.get(1) != null && !dataList.get(1).equals("")) {
			String[] ids = dataList.get(1).split(",");
			List<String> idList = Arrays.asList(ids);
			/** 查询任务 */
			List<String> taskList = queryUnlockTaskById(idList);
			Long count = 0L;
			while(taskList==null || taskList.size() != idList.size()){
				//刚放入redis，没提交事务。
				Thread.sleep(500 *(count+1));
				taskList = queryUnlockTaskById(idList);
				count ++;
				if(count >3){
					batchLockDsipStsById(idList, "N");
					throw new RuntimeException("获取任务数据与调度者不符.");
				}
			}
			return taskList;
		}

		return new ArrayList<String>(1);
	}

	private void batchLockDsipStsById(List<String> idList, String n) {
		try {
			TimeUnit.MICROSECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private List<String> queryUnlockTaskById(List<String> idList) {
		try {
			TimeUnit.MICROSECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return idList;
	}

}
