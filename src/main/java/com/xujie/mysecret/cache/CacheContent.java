package com.xujie.mysecret.cache;

import com.xujie.mysecret.utils.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
@Component
@Slf4j
public class CacheContent implements ICache<String, String> {

    @Override
    public void save(String key, String value) {
        CacheManager.CACHE.put(key, value);
    }

    @Override
    public String get(String key) throws Exception {

        return CacheManager.CACHE.get(key, () -> {
            log.info("key为:{},当前缓存为空，获取数据并缓存...", PREFIX + TICKET);
            if((PREFIX + ACCESSTOKEN).equals(key)){
                return WechatConfig.getWechatAccessToken();
            }else if((PREFIX + TICKET).equals(key)) {
                return WechatConfig.getTicket(this.get(PREFIX + ACCESSTOKEN));
            }
            return null;
        });
    }
}