package com.artist.sbgame.component;

import com.artist.sbgame.entity.ElectricityConsumption;
import com.artist.sbgame.entity.LatestElectricityConsumption;
import com.artist.sbgame.dao.DaoService;
import com.artist.sbgame.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Component
@Slf4j
public class AtomicUtil {
    @Autowired
    private DaoService daoService;
    @Autowired
    private RedisService redisService;

    /**
     * 事务，原子性
     * 1.回写mysql的【应缴电费表】
     * 2.写redis设置Redis的user_id未缴费状态
     * 3.propagation = Propagation.REQUIRED,
     */
    @Transactional(rollbackFor = Exception.class,
            isolation = Isolation.REPEATABLE_READ)
    public void atomicDataOptions(ElectricityConsumption ec) {
        int user_id = ec.getUser_id();
        LatestElectricityConsumption lec = daoService.getLatestElectricityConsumption(user_id);
        if(lec != null){
            log.info("消费者workerTaskAsync--"
                    +Thread.currentThread().getName()
                    +"--事务开始--atomicDataOptions--|当前电费:"
                    +ec.getUser_CurrentElectricityConsumption()
                    +"|上次电费:"+lec.getLatestElectricityConsumption());
            BigDecimal electricity_bills = ec.getUser_CurrentElectricityConsumption()
                    .subtract(lec.getLatestElectricityConsumption());
            if(electricity_bills.compareTo(BigDecimal.ZERO) > 0){
                daoService.updateLatestElectricityConsumption(ec);
                daoService.insertElectricityBills(user_id,electricity_bills);
                redisService.setIdForBills((long)user_id);
                log.info("消费者workerTaskAsync--"
                        +Thread.currentThread().getName()
                        +"--发邮件{id:"+user_id+",电费账单:"+electricity_bills+"}");
            }
            log.info("消费者workerTaskAsync--"+Thread.currentThread().getName()+"--事务结束--atomicDataOptions");
        }
    }
}
