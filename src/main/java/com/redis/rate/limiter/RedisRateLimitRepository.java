package com.redis.rate.limiter;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisRateLimitRepository {

    private final StringRedisTemplate redisTemplate;

    public Long increment(String key) {

        return redisTemplate.opsForValue().increment(key);

    }

    public void expire(String key, Duration ttl) {

        redisTemplate.expire(key, ttl);

    }

    public Long getTTL(String key) {

        return redisTemplate.getExpire(key);

    }

}
