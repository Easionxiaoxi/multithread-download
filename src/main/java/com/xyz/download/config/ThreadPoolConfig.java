package com.xyz.download.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的配置
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * 线程池配置
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor getAsyncExecutors() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(5);
        // 最大线程数
        executor.setMaxPoolSize(10);
        // 工作队列容量,默认无界队列LinkedBlockingQueue
        executor.setQueueCapacity(1000);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("download-thread-");
        // 不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
