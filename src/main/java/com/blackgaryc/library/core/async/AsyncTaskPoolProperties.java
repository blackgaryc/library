package com.blackgaryc.library.core.async;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "async.task.pool")
public class AsyncTaskPoolProperties {
    /**
     * 核心线程池
     */
    private int corePoolSize = 5;
    /**
     * 最大线程池
     */
    private int maxPoolSize = 50;
    /**
     * 线程空闲时间
     */
    private int keepAliveSeconds = 60;
    /**
     * 队列长度
     */
    private int queueCapacity = 10000;
    /**
     * 线程名称前缀
     */
    private String threadNamePrefix = "TX-AsyncTask-";

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }
}