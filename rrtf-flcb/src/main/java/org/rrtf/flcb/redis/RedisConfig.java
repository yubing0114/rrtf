package org.rrtf.flcb.redis;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 负责redis缓存配置过程中自动生成key
 * 
 * @author yubing
 *
 */
@Configuration
@EnableCaching // 开启缓存
public class RedisConfig extends CachingConfigurerSupport {

	// 自定义RedisTemplate序列化存储对象的模式
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		//以json的格式最终返回object对象
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		//负责将object对象转换为字符串或反之
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(mapper);
		template.setValueSerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}

	// 添加缓存管理的bean
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
		// 设置缓存时间
		redisCacheManager.setDefaultExpiration(60 * 30);
		return redisCacheManager;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		// 以匿名内部类的形式返回KeyGenerator对象
		return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				// 依次在缓冲区中拼接类名+方法名+参数列表，可以构成一个唯一的key
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object param : params) {
					sb.append(param);
				}
				return sb.toString();// 将拼接成的字符串返回
			}
		};
	}
}
