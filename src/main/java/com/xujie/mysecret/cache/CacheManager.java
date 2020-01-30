package com.xujie.mysecret.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.Data;
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
@Data
public class CacheManager{

    public static Cache<String, String> WECHATCACHE;
    public static Cache<String,String> DICTIONARY;

    @PreDestroy
    public void destroy(){
        WECHATCACHE.cleanUp();
        log.info("应用关闭，缓存清理成功！");
    }

    public static void buildCache() {
        log.info("初始化缓存容器....");
        WECHATCACHE = CacheBuilder.newBuilder()
                .maximumSize(1000)
                //缓存保留1小时
                .expireAfterWrite(3600, TimeUnit.SECONDS)
                //缓存移除监听器
                .removalListener((RemovalListener<String, String>) notify -> {
                    log.info("缓存项被删除,key:{},value为:{},删除原因为:{}", notify.getKey(), notify.getValue(), notify.getCause().name());
                })
                .build();

        /*DICTIONARY = CacheBuilder.newBuilder()
                .maximumSize(1000)
                //缓存保留1个月
                .expireAfterWrite(2592000, TimeUnit.SECONDS)
                //缓存移除监听器
                .removalListener((RemovalListener<String, String>) notify -> {
                    log.info("缓存项被删除,key:{},value为:{},删除原因为:{}", notify.getKey(), notify.getValue(), notify.getCause().name());
                })
                .build();*/

    log.info("容器初始化完毕...");
    }
}
