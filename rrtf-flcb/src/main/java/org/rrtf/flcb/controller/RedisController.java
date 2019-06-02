package org.rrtf.flcb.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;

@RestController
public class RedisController {
	@Autowired
	RedisTemplate redisTemplate;
	@RequestMapping("/redis/set/{key}/{value}")
	public void redisGetKey(@PathVariable("key") String key, @PathVariable("value") String value) {
		Jedis jedis = new Jedis("192.168.1.20");
		System.out.println("连接成功");
		System.out.println(jedis.ping());
		 jedis.set(key, value);
	}
	
	
	@RequestMapping("/redis/get/{key}")
	public void redisGetKey(@PathVariable("key") String key) {
		
		ValueOperations<String,Object> operations = redisTemplate.opsForValue();
		JSONObject object = (JSONObject) operations.get(key);
		User user = object.toJavaObject(User.class);
		System.out.println(object);
		System.out.println(user);
//		 return value;
	}
}
