package com.artist.sbgame.dao.impl;

import com.artist.sbgame.entity.ElectricityConsumption;
import com.artist.sbgame.entity.LatestElectricityConsumption;
import com.artist.sbgame.entity.Mapper.ElectricityBillsMapper;
import com.artist.sbgame.entity.Mapper.LatestElectricityConsumptionMapper;
import com.artist.sbgame.dao.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public class DaoServiceImpl implements DaoService {
    @Autowired
    private LatestElectricityConsumptionMapper latestElectricityConsumptionMapper;
    @Autowired
    private ElectricityBillsMapper electricityBillsMapper;

    @Override
    public LatestElectricityConsumption getLatestElectricityConsumption(int user_id) {
        return latestElectricityConsumptionMapper.getLatestElectricityConsumption(user_id);
    }

    @Override
    public void updateLatestElectricityConsumption(ElectricityConsumption ec) {
        latestElectricityConsumptionMapper.updateLatestElectricityConsumption(ec.getUser_id(),ec.getUser_CurrentElectricityConsumption());
    }

    @Override
    public void insertElectricityBills(int userId, BigDecimal electricityBills) {
        electricityBillsMapper.insertElectricityBills(userId,electricityBills);
    }

    @Override
    public void deleteRecordFromElectricityBills(int userId) {
        electricityBillsMapper.deleteRecordFromElectricityBills(userId);
    }

}
