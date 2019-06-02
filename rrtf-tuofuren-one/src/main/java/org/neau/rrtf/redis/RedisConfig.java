package org.neau.rrtf.redis;

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
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

//负责Redis缓存配置自动生成key的
@Configuration
@EnableCaching // 开启缓存注解
public class RedisConfig extends CachingConfigurerSupport {
	// 自定义RedisTemplate序列化对象的格式
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		System.out.println("程序执行...");
		StringRedisTemplate template = new StringRedisTemplate(factory);
		// 以JSON语法格式最终返回Object对象信息
		Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();// 负责将Object对象转换成字符串或反之
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(om);
		template.setValueSerializer(serializer);
		template.afterPropertiesSet();
		return template;
	}

	// 添加缓存管理的Bean
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// 添加缓存管理的配置,例如：设置缓存的过期时间
		cacheManager.setDefaultExpiration(60 * 30);
		return cacheManager;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		// 以匿名内部类形式返回KeyGenerator对象
		return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				// 依次在字符串缓冲区中拼接类名+方法名+参数列表,可以构成一个唯一的key
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object param : params) {
					sb.append(param);
				}
				return sb.toString();// 最后将拼接成的字符串返回
			}

		};
	}
}
