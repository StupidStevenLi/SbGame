package com.artist.sbgame.controller;

import com.artist.sbgame.component.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CountDownLatch;

@Controller
@EnableKafka
@Slf4j
public class Consumer {
    @Autowired
    private ThreadUtil threadUtil;
    @KafkaListener(id = "oneConsumerManyThread",
                   groupId = "defaultConsumerGroup",
                   topicPartitions = {@TopicPartition(topic = "test-topic",partitions = "0" )}
    )
    public void consumerListener(ConsumerRecords<?,?> records) {
        log.info(Thread.currentThread().getName()+"--消息监听开始：本次消息数量"+ records.count());
        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(records.count());
        for (ConsumerRecord<?, ?> record : records) {
            threadUtil.workerTaskAsync(record, countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName()+"--消息监听结束：线程池任务完成");
    }
}
