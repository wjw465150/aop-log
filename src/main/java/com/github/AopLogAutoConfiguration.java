package com.github;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.github.collector.LogCollector;
import com.github.collector.NothingCollector;

/**
 * @author EalenXie create on 2021/1/4 11:19
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties(ThreadPoolProperties.class)  //使使用 @ConfigurationProperties 注解的类生效。
public class AopLogAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AopLogAutoConfiguration.class);

    @Autowired
    private ThreadPoolProperties threadPoolProperties;
    
    /**
     * 默认配置一个空的收集器
     *
     * @return By default, an empty collector is configured
     */
    @Bean
    @ConditionalOnMissingBean(value = LogCollector.class)
    public LogCollector nothingCollector() {
        return new NothingCollector();
    }


    /**
     * 默认配置异步收集器线程池
     *
     * @return The asynchronous collector thread pool is configured by default
     */
    @Bean
    @ConditionalOnMissingBean(name = "collectorAsyncExecutor")
    public Executor collectorAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        executor.setThreadNamePrefix("aop-log-collectorAsyncExecutor-");
        executor.setRejectedExecutionHandler((r, exec) -> log.error("aop-log-collectorAsyncExecutor thread queue is full,activeCount:{},Subsequent collection tasks will be rejected,please check your LogCollector or config your Executor", exec.getActiveCount()));
        executor.initialize();
        return executor;
    }
}
