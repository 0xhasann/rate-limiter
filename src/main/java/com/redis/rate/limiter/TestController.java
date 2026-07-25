package com.redis.rate.limiter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/rate")
    public String test() {

        log.info("Controller test ::");

        return "hello";
    }

}
