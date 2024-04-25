package com.artist.sbgame.service.impl;

import com.artist.sbgame.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Value(value = "${IdInDebtRedis}")
    private String IdInDebtRedis;
    //DAO
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setIdForBills(Long key) {
        stringRedisTemplate.opsForValue().setBit(IdInDebtRedis, key,true);
    }

    @Override
    public  Boolean isIdForBills(long id) {
        return stringRedisTemplate.opsForValue().getBit(IdInDebtRedis, id);
    }

    @Override
    public void deleteIdForBills(int key) {
        stringRedisTemplate.opsForValue().setBit(IdInDebtRedis, key,false);
    }

}
