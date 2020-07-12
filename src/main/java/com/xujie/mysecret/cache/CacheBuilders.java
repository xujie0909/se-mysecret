package com.xujie.mysecret.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author xujie17
 */
@Slf4j
@Component
@Data
public class CacheBuilders {

    public static Cache<String, String> WECHATCACHE;

    @PreDestroy
    public void destroy() {

    }

    public static void buildCache() {

        log.info("初始化缓存....");

        WECHATCACHE = CacheBuilder.newBuilder()
                .maximumSize(1000)
                //缓存保留1小时
                .expireAfterWrite(3600, TimeUnit.SECONDS)
                //缓存移除监听器
                .removalListener((RemovalListener<String, String>) notify -> {
                    log.info("微信缓存项被删除,key:{},value为:{},删除原因为:{}", notify.getKey(), notify.getValue(), notify.getCause().name());
                })
                .build();

        log.info("缓存初始化完毕...");
    }
}
