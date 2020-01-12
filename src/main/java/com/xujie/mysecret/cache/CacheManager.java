package com.xujie.mysecret.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author xujie17
 */
@Slf4j
@Component
@Order(value = 1)
public class CacheManager implements CommandLineRunner {

    public static Cache<String, String> CACHE;

    @PreDestroy
    public void destroy(){
        log.info("应用要关机了.....清理下缓存..");
        CACHE.cleanUp();
    }

    @Override
    public void run(String... args) {
        log.info("应用要启动了，开始初始化缓存了....");
        CACHE = CacheBuilder.newBuilder()
                .maximumSize(1000)
                //缓存保留2小时
                .expireAfterWrite(7200, TimeUnit.SECONDS)
                //缓存移除监听器
                .removalListener((RemovalListener<String, String>) notify -> {
                    log.info("缓存项被删除：{}>=<{},删除原因：{}", notify.getKey(), notify.getValue(), notify.getCause().name());
                })
                .build();
    }
}
