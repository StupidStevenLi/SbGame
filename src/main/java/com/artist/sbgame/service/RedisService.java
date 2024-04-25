package com.artist.sbgame.service;

public interface RedisService {
    void setIdForBills(Long key);

    Boolean isIdForBills(long id);

    void deleteIdForBills(int userId);
}
