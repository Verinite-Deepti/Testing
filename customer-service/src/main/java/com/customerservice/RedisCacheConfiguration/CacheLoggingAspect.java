package com.customerservice.RedisCacheConfiguration;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheLoggingAspect {

    @AfterReturning(pointcut = "@annotation(cacheable)", returning = "result")
    public void logCacheHit(Cacheable cacheable, Object result) {
        if (result != null) {
            // Log cache hit
            System.out.println("Cache hit for key: " + cacheable.key());
        } else {
            // Log cache miss
            System.out.println("Cache miss for key: " + cacheable.key());
        }
    }
}
