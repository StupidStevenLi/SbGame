package com.artist.sbgame;

import com.artist.sbgame.component.MailUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SbGameApplicationTests {

    @Resource
    private MailUtil mailUtil;

    @Test
    void contextLoads() {
        log.info("这是日志");
    }

}
