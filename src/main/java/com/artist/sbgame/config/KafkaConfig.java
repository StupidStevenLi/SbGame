package com.artist.sbgame.config;

import jakarta.annotation.Resource;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConfig {

    //"ChargeTopic"
    @Value(value = "${TEST_TOPIC}")
    private String TEST_TOPIC;
    @Resource
    private ConsumerFactory<String, Object> consumerFactory;

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(TEST_TOPIC, 1, (short) 1);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> container = new ConcurrentKafkaListenerContainerFactory<String, Object>();
        container.setConsumerFactory(consumerFactory);
        //禁止KafkaListener自启动
        container.setAutoStartup(false);
        return container;
    }
}
