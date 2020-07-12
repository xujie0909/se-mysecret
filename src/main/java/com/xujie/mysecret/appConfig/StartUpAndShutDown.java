package com.xujie.mysecret.appConfig;

import com.xujie.mysecret.cache.CacheBuilders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xujie
 */
@Component
@Order(value = 1)
@Slf4j
public class StartUpAndShutDown implements CommandLineRunner {

    @Override
    public void run(String... args) {
        //加载缓存
        CacheBuilders.buildCache();
        log.info("缓存加载成功！");
    }
}
