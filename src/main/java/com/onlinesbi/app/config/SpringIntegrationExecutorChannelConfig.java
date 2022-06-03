package com.onlinesbi.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringIntegrationExecutorChannelConfig {

    public static final int ASSIGN_GATEWAY_TASK_EXECUTOR_CORE_POOL_SIZE = 5;
    public static final int ASSIGN_GATEWAY_TASK_EXECUTOR_MAX_POOL_SIZE = 25;
    public static final int ASSIGN_GATEWAY_TASK_EXECUTOR_QUEUE_CAPACITY = 100;

    @Bean
    public ThreadPoolTaskExecutor accountVerificationChannelThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_CORE_POOL_SIZE);
        executor.setMaxPoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_MAX_POOL_SIZE);
        executor.setQueueCapacity(ASSIGN_GATEWAY_TASK_EXECUTOR_QUEUE_CAPACITY);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Bean
    public MessageChannel accountVerificationChannel(){
        return new ExecutorChannel(accountVerificationChannelThreadPool());
    }


    @Bean
    public ThreadPoolTaskExecutor invalidAccountErrorResponseChannelThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_CORE_POOL_SIZE);
        executor.setMaxPoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_MAX_POOL_SIZE);
        executor.setQueueCapacity(ASSIGN_GATEWAY_TASK_EXECUTOR_QUEUE_CAPACITY);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Bean
    public MessageChannel invalidAccountErrorResponseChannel(){
        return new ExecutorChannel(invalidAccountErrorResponseChannelThreadPool());
    }

    @Bean
    public ThreadPoolTaskExecutor httpResponseChannelChannelThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_CORE_POOL_SIZE);
        executor.setMaxPoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_MAX_POOL_SIZE);
        executor.setQueueCapacity(ASSIGN_GATEWAY_TASK_EXECUTOR_QUEUE_CAPACITY);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }


    @Bean
    public MessageChannel httpResponseChannel(){
        return new ExecutorChannel(httpResponseChannelChannelThreadPool());
    }

    @Bean
    public ThreadPoolTaskExecutor fundVerificationServiceChannelThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_CORE_POOL_SIZE);
        executor.setMaxPoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_MAX_POOL_SIZE);
        executor.setQueueCapacity(ASSIGN_GATEWAY_TASK_EXECUTOR_QUEUE_CAPACITY);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Bean
    public MessageChannel fundVerificationServiceChannel(){
        return new ExecutorChannel(fundVerificationServiceChannelThreadPool());
    }

    @Bean
    public ThreadPoolTaskExecutor insufficientFundErrorResponseChannelThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_CORE_POOL_SIZE);
        executor.setMaxPoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_MAX_POOL_SIZE);
        executor.setQueueCapacity(ASSIGN_GATEWAY_TASK_EXECUTOR_QUEUE_CAPACITY);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Bean
    public MessageChannel insufficientFundErrorResponseChannel(){
        return new ExecutorChannel(insufficientFundErrorResponseChannelThreadPool());
    }

    @Bean
    public ThreadPoolTaskExecutor fundTransferCompletedChannelThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_CORE_POOL_SIZE);
        executor.setMaxPoolSize(ASSIGN_GATEWAY_TASK_EXECUTOR_MAX_POOL_SIZE);
        executor.setQueueCapacity(ASSIGN_GATEWAY_TASK_EXECUTOR_QUEUE_CAPACITY);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Bean
    public MessageChannel fundTransferCompletedChannel(){
        return new ExecutorChannel(fundTransferCompletedChannelThreadPool());
    }


}
