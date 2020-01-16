package com.xujie.mysecret.appConfig;

import com.xujie.mysecret.cache.CacheManager;
import com.xujie.mysecret.service.impl.WeChatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
@Slf4j
public class StartUpAndShutDown implements CommandLineRunner {

    private final CacheManager cacheManager;

    private final WeChatServiceImpl weChatService;

    public StartUpAndShutDown(CacheManager cacheManager, WeChatServiceImpl weChatService) {
        this.cacheManager = cacheManager;
        this.weChatService = weChatService;
    }


    @Override
    public void run(String... args) {

        //加载缓存
        cacheManager.buildCache();
        log.info("缓存加载成功！");

        //加载微信菜单
        Integer errCode = weChatService.createMenu();
        if (0 == errCode) {
            log.info("微信菜单加载成功！");
        } else {
            log.error("微信菜单加载失败！");
        }
    }
}
