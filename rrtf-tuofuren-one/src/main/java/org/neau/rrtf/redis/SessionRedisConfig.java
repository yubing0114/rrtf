package org.neau.rrtf.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=30*60)//设置缓存session的过期时间
public class SessionRedisConfig {

}
