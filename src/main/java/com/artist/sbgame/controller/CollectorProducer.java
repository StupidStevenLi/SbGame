package com.artist.sbgame.controller;

import com.artist.sbgame.component.MailUtil;
import com.artist.sbgame.dao.DaoService;
import com.artist.sbgame.entity.ElectricityConsumption;
import com.artist.sbgame.entity.MailUser;
import com.artist.sbgame.service.KafkaService;
import com.artist.sbgame.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class CollectorProducer {
    @Autowired
    private KafkaService kafkaService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private DaoService daoService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private MailUtil mailUtil;

    @PostMapping("/collector")
    @ResponseBody
    public String collect(@RequestBody ElectricityConsumption electricityConsumption){
        kafkaService.makeElectricityConsumptionGetInKafka(electricityConsumption);
        return electricityConsumption.toString();
    }

    @PostMapping("/send")
    @ResponseBody
    public String sendOne(@RequestBody ElectricityConsumption electricityConsumption) {
        kafkaService.makeElectricityConsumptionGetInKafka(electricityConsumption);
        log.info("单次"+electricityConsumption);
        return electricityConsumption.toString();
    }

    @PostMapping("/pay")
    @ResponseBody
    public String payAndSendMail(@RequestBody MailUser mailUser){
        int user_id = mailUser.getUser_id();
        String mailId = mailUser.getMail();
        //事务
        RLock rLock = redissonClient.getLock("UserPayingLock");
        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        try {
            boolean isLock = rLock.tryLock(100, 10, TimeUnit.SECONDS);
            if (isLock) {
                if (redisService.isIdForBills(user_id)) {
                    redisService.deleteIdForBills(user_id);
                    daoService.deleteRecordFromElectricityBills(user_id);
                    //mailUtil.sendTemplateMail(mailId,"电费支付成功","客户:"+user_id,"您的电费已支付成功");
                    log.info("发邮件告知redisson事务操作成功：支付成功" + mailId);
                }
            }
        } catch (Exception ex) {
            log.info("发邮件告知redisson事务操作失败：支付失败"+mailId);
            try {
                //mailUtil.sendTemplateMail(mailId,"电费支付失败","客户:"+user_id,"您的电费未支付成功");
            } catch (Exception e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
            finally {
                ex.printStackTrace();
            }
        }
        finally {
            rLock.unlock();
            log.info("释放锁");
        }
        log.info("payAndSendMail支付并且发了对应邮件：支付操纵流程结束");
        return mailId+"邮件发送完成";
    }
    @GetMapping("/")
    public String welcome(){
        return "Index";
    }
}
