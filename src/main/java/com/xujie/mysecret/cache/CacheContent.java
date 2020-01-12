package com.xujie.mysecret.cache;

import com.xujie.mysecret.utils.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xujie17
 */
@Component
@Slf4j
public class CacheContent implements ICache<String,String> {

    @Override
    public void save(String key, String value) {
        CacheManager.CACHE.put(key,value);
    }

    @Override
    public String get(String key) throws Exception {

        return CacheManager.CACHE.get(key, () -> {

            log.info("当前缓存为空，获取数据并缓存...");

            String accessToken = WechatConfig.getWechatAccessToken();
            String ticket = WechatConfig.getTicket(accessToken);

            log.info("ticket缓存成功:{}",ticket);
            log.info("key缓存成功:{}",key);

            return ticket;
        });

    }
}
