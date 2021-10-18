package com.example.apitest.service;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * redis 에 생성한 로그인 토큰 insert
     *
     * @param token
     * @param expirationDate
     */
    public void insertLoginTokenRedis(String token, String expirationDate) {
        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(token, expirationDate);
    }

    /**
     * redis 에서 token 만료 여부 확인
     *
     * @param token
     * @return
     */
    public boolean isTokenExpiredRedis(String token) {
        boolean result = true;

        // redis 에 저장된 토큰의 만료시간 꺼낸다.
        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        final String value = valueOperations.get(token);
        // 해당 토큰이 redis 에 존재할때 만료여부 체크. 없으면 만료(true) 리턴한다.
        if (ObjectUtils.isNotEmpty(value)) {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime expiredDate = LocalDateTime.parse(value);
            if (!expiredDate.isBefore(currentTime)) { // expiredDate 이 현재 시간보다 과거가 아닐 때 만료 여부 false 리턴
                result = false;
            }
        }
        return result;
    }

}
