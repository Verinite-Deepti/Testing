package com.customerservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerservice.service.CachingEnvService;

@RestController
public class CachingEnvController {

    private final CachingEnvService cachingenvService;

    public CachingEnvController(CachingEnvService cachingenvService) {
        this.cachingenvService = cachingenvService;
    }

    @GetMapping("/getData")
    public String getData(@RequestParam String input) {
        return cachingenvService.getCachedData(input);
    }
}
