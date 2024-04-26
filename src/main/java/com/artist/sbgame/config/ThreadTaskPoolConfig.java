package com.artist.sbgame.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Slf4j
public class ThreadTaskPoolConfig {
    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 获取cpu线程数
        int cpuNum = Runtime.getRuntime().availableProcessors();
        log.info("获取cpu线程数" + Runtime.getRuntime().availableProcessors());
        // 核心线程数
        threadPoolTaskExecutor.setCorePoolSize(cpuNum / 2);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(cpuNum);
        // 线程空闲后的最大存活时间 单位 秒
        threadPoolTaskExecutor.setKeepAliveSeconds(2);
        // 队列大小
        threadPoolTaskExecutor.setQueueCapacity(cpuNum * 200);
        // 线程前置名称
        threadPoolTaskExecutor.setThreadNamePrefix("线程池-AsyncTaskExecutor-ThreadPoolTask-");
        //当调度器shutdown被调用时等待当前被调度的任务完成
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        /*
         * description: 配置拒绝策略
         * rejectedExectutionHandler参数字段用于配置绝策略，常用拒绝策略如下：
         * AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
         * CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
         * DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
         * DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
         */
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //加载
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
