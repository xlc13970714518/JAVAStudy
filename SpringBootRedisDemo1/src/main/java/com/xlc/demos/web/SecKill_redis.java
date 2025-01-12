package com.xlc.demos.web;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

public class SecKill_redis {

	public static void main(String[] args) {
		Jedis jedis =new Jedis("localhost",6379);
		System.out.println(jedis.ping());
		jedis.close();
	}

	//秒杀过程
	public static boolean doSecKill(String uid,String prodid) throws IOException {
		//1 uid和prodid非空判断
		if(uid == null || prodid == null) {
			return false;
		}

		//2 连接redis
		Jedis jedis = new Jedis("172.22.109.205",6379);

		//3 拼接key
		// 3.1 库存key
		String kcKey = "sk:"+prodid+":qt";
		// 3.2 秒杀成功用户key
		String userKey = "sk:"+prodid+":user";

		//4 获取库存，如果库存null，秒杀还没有开始
		String kc = jedis.get(kcKey);
		if(kc == null) {
			System.out.println("秒杀还没有开始，请等待");
			jedis.close();
			return false;
		}

		// 5 判断用户是否重复秒杀操作
		if(jedis.sismember(userKey, uid)) {
			System.out.println("已经秒杀成功了，不能重复秒杀");
			jedis.close();
			return false;
		}
		
		
	    //因为kc为字符串，所以先转换城integer类型的
		//6 判断如果商品数量，库存数量小于1，秒杀结束
		if(Integer.parseInt(kc)<=0) {
			System.out.println("秒杀已经结束了");
			jedis.close();
			return false;
		}


		//7.1 库存-1
		jedis.decr(kcKey);
		//7.2 把秒杀成功用户添加清单里面
		jedis.sadd(userKey,uid);

		System.out.println("秒杀成功了..");
		jedis.close();
		return true;
	}
  /**
 * 乐观锁解决超卖问题  但是 存在并发问题 有一个用户拿到库存为1，然后减1 由于版本变了，后面的都不能拿到了
 *
 * */
	void test(){
		Jedis jedis = new Jedis("172.22.109.205",6379);
		String prodid = "1001";
		String uid = "1001";
		//3 拼接key
		// 3.1 库存key
		String kcKey = "sk:"+prodid+":qt";
		// 3.2 秒杀成功用户key
		String userKey = "sk:"+prodid+":user";
		//加入一个监视的watch
		jedis.watch(kcKey);
		//7 秒杀过程
       //使用事务
		Transaction multi = jedis.multi();
		//组队操作
		multi.decr(kcKey);
		multi.sadd(userKey,uid);
		//执行
		List<Object> results = multi.exec();

		if(results == null || results.size()==0) {
			System.out.println("秒杀失败了....");
			jedis.close();
			return;
		}
		//7.1 库存-1
        //jedis.decr(kcKey);
        //7.2 把秒杀成功用户添加清单里面
        //jedis.sadd(userKey,uid);

		System.out.println("秒杀成功了..");
		jedis.close();
	}
}
