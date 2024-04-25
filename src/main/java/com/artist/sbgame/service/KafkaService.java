package com.artist.sbgame.service;

import com.artist.sbgame.entity.ElectricityConsumption;

public interface KafkaService {
    void makeElectricityConsumptionGetInKafka(ElectricityConsumption electricityConsumption);
}
