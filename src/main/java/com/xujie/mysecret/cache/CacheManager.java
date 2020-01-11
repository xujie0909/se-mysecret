package com.xujie.mysecret.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author xujie17
 */
@Slf4j
@Component
public class CacheManager implements ApplicationListener<ContextRefreshedEvent> {

    private static Cache<String, String> CACHE;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        CACHE = initCache();
    }

    public Cache<String, String> initCache() {
        return CacheBuilder.newBuilder()
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
