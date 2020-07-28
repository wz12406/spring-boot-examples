package com.neo.np2p;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Np2pReactorProducer {

	@Autowired
	JedisPool jedisPool;


	public static int totalTask = 100000;

	/**
	 * 使用redis 列表实现生产者效费者模式
	 * 一个线程生产数据  1秒消费一个
	 * 4个线程消费数据  每两秒消费一个
	 *效果： 避免出现并发消费
	 */
	@Test
	public void testRedis() throws InterruptedException {
		Jedis jedis = jedisPool.getResource();
		String[] keys = {"test_s_t_key1","test_s_t_key2","test_s_t_key3","test_s_t_key4"};
		try {
			while (true){
				for (String key : keys) {

					if (jedis.llen(key) < 1) {

						try {
							//获取对应任务
							List<String> taskIdList = getTaskList();
							if (taskIdList != null && taskIdList.size() > 0) {
								//构建队列保存值
								List<String> dataList = buildQueueValue(taskIdList,200);
								String[] ids = dataList.toArray(new String[dataList.size()]);
								//写入消息队列
								jedis.rpush(key, ids);
							} else {

								//休眠时间
								long sleepTime = 3000;
								Thread.sleep(sleepTime);
							}
						} catch (Exception e) {
							log.error("AbstractDispather.run()", e);
						}

					}
				}
				log.info("----生产任务完成，休息5秒");
				//休眠时间
				Thread.sleep(500l);
			}
		} catch (Exception e) {
			log.error("AbstractDispather.run()", e);
		}

		int millis = 1000000000;
		Thread.sleep(millis);
	}

	private List<String> buildQueueValue(List<String> taskIdList, int length) {
		List<String> rtnList = new ArrayList<String>();

		int i = 0;
		StringBuffer sbf = new StringBuffer();

		for (String str : taskIdList) {

			sbf.append(str).append(",");
			//计数
			++i;

			if (i == length) {
				//
				sbf.deleteCharAt(sbf.length()-1);
				rtnList.add(sbf.toString());
				//初始化
				i = 0;
				sbf.delete(0, sbf.length());
			}
		}

		if (sbf.length()>1) {

			sbf.deleteCharAt(sbf.length()-1);
			rtnList.add(sbf.toString());
		}

		return rtnList;
	}

	private List<String> getTaskList() {
		List<String> result = new ArrayList();
		for(int i = 0 ;i<10000;i++){
			result.add(IdUtil.simpleUUID());
		}
		return result;
	}

}
