package com.artist.sbgame.service.impl;

import com.artist.sbgame.entity.ElectricityConsumption;
import com.artist.sbgame.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Value(value = "${TEST_TOPIC}")
    private String TEST_TOPIC;

    @Override
    public void makeElectricityConsumptionGetInKafka(ElectricityConsumption electricityConsumption) {
        kafkaTemplate.send(TEST_TOPIC, 0, Integer.toString(electricityConsumption.getUser_id()), electricityConsumption);
    }
}
