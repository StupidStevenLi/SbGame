package com.artist.sbgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class SbGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbGameApplication.class, args);
    }

}
