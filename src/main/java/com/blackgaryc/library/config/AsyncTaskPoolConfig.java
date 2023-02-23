package com.blackgaryc.library.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Resource;

import com.blackgaryc.library.core.async.AsyncTaskPoolProperties;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 异动线程池配置
 */
@Configuration
public class AsyncTaskPoolConfig implements AsyncConfigurer {

    @Resource
    private AsyncTaskPoolProperties asyncTaskPoolProperties;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncTaskPoolProperties.getCorePoolSize());
        executor.setMaxPoolSize(asyncTaskPoolProperties.getMaxPoolSize());
        executor.setKeepAliveSeconds(asyncTaskPoolProperties.getKeepAliveSeconds());
        executor.setQueueCapacity(asyncTaskPoolProperties.getQueueCapacity());
        executor.setThreadNamePrefix(asyncTaskPoolProperties.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setAllowCoreThreadTimeOut(true);
        // 线程池初始化
        executor.initialize();
        return executor;
    }

    /**
     * 异常处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                System.out.println("throwable = " + throwable + ", method = " + method + ", objects = " +
                        Arrays.deepToString(objects));
            }
        };
    }
}