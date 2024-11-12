package com.customerservice.RedisCacheConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.NoOpCacheManager;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableCaching
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    @Profile("qa")
    public RedisConnectionFactory redisConnectionFactory() {
        logger.info("QA profile active: Configuring RedisConnectionFactory");
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    @Profile("qa")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        logger.info("QA profile active: Configuring RedisTemplate");
        return template;
    }

    @Bean
    @Profile("qa")
    public CacheManager redisCacheManager() {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5)) 
                .disableCachingNullValues();
        logger.info("QA profile active: Configuring RedisCacheManager");
        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .build();
    }

    @Bean
    @Profile("dev")
    public CacheManager devCacheManager() {
        logger.info("Development profile active: Configuring ConcurrentMapCacheManager");
        return new ConcurrentMapCacheManager();
    }

    @Bean
    @Profile("local")
    public CacheManager localCacheManager() {
        logger.info("Local profile active: Configuring NoOpCacheManager");
        return new NoOpCacheManager();
    }
}
