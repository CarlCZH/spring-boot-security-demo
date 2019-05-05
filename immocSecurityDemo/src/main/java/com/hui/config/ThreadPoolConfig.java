package com.hui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: CarlChen
 * @Despriction: ThreadPoolTaskExecutor 线程池配置
 * @Date: Create in 21:36 2019\3\11 0011
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolExecutor(){
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        pool.setCorePoolSize(50);
        // 线程池维护线程的最大数量，默认为Integer.MAX_VALUE
        pool.setMaxPoolSize(1000);
        // 线程池所使用的缓冲队列，一般需要设置值>=notifyScheduledMainExecutor.maxNum；默认为Integer.MAX_VALUE
        pool.setQueueCapacity(20000);
        // 线程池维护线程所允许的空闲时间，默认为60s
        pool.setKeepAliveSeconds(300);
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

}
