package com.artist.sbgame.dao;

import com.artist.sbgame.entity.ElectricityConsumption;
import com.artist.sbgame.entity.LatestElectricityConsumption;

import java.math.BigDecimal;

public interface DaoService {
    LatestElectricityConsumption getLatestElectricityConsumption(int user_id);

    void updateLatestElectricityConsumption(ElectricityConsumption ec);

    void insertElectricityBills(int userId, BigDecimal electricityBills);

    void deleteRecordFromElectricityBills(int userId);
}
