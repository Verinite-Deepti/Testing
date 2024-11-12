package com.customerservice.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachingEnvService {

    @Cacheable("myCache")
    public String getCachedData(String input) {
        
        try {
            Thread.sleep(2000); 
            // 2 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Cached Data for: " + input;
    }
}
