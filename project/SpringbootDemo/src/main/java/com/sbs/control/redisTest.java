package com.sbs.control;

import redis.clients.jedis.Jedis;

public class redisTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
      //设置 redis 字符串数据
        jedis.set("lixin", "www.lixin.com");
        System.out.println(jedis.get("lixin"));
	}

}
