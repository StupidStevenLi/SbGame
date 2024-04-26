package com.artist.sbgame.component;

import com.artist.sbgame.entity.ElectricityConsumption;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@EnableAsync
@Slf4j
public class ThreadUtil {

    @Autowired
    private AtomicUtil atomicUtil;

    @Async("asyncTaskExecutor")
    public void workerTaskAsync(ConsumerRecord<?, ?> record, CountDownLatch countDownLatch) {
        log.info("消费者workerTaskAsync--"
                + Thread.currentThread().getName()
                + "消费开始："
                + "|offset:" + record.offset());
        ElectricityConsumption ec = (ElectricityConsumption) record.value();
        //事务
        atomicUtil.atomicDataOptions(ec);
        /**
         * 异步发邮件
         */
        log.info("消费者workerTaskAsync--"
                + Thread.currentThread().getName()
                + "消费完成:"
                + record.key() + "存在发邮件,不存在不发邮件");
        countDownLatch.countDown();
    }
}
