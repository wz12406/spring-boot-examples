package com.neo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ThreadTests {

	static volatile int flag = 1; //1 打印Y  2打印XX

	private static ReentrantLock lock = new ReentrantLock();


	public static void main(String[] args) throws InterruptedException {
		new Thread(()->{
			while (true){
				try {
					lock.lock();
					if(flag == 2){
						System.out.print("XX");
						flag = 1;
					}

				}catch (Exception e){
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
			}
		}).start();

		new Thread(()->{
				while (true){
					try{
						lock.lock();
						if(flag == 1){
							System.out.print("Y");
							flag = 2;
						}
					}catch (Exception e){
						e.printStackTrace();
					}finally {
						lock.unlock();
					}

				}
		}).start();

		TimeUnit.SECONDS.sleep(1000);

		BlockingQueue queue = new ArrayBlockingQueue<>(5);
	}
}
