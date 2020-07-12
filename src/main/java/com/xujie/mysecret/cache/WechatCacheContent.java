package com.xujie.mysecret.cache;

import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.utils.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
@Component
@Slf4j
public class WechatCacheContent {

    @Value("${my.secret.wechat.appid}")
    private String appid;

    @Value("${my.secret.wechat.appsecret}")
    private String appsecret;

    @Value("${my.secret.wechat.ticketurl}")
    private String ticketurl;

    @Value("${my.secret.wechat.grantType}")
    private String grantType;

    public void save(String key, String value) {
        CacheBuilders.WECHATCACHE.put(key, value);
    }

    public String get(String key) throws Exception {

        return CacheBuilders.WECHATCACHE.get(key, () -> {
            log.info("key为:{},当前缓存为空，获取数据并缓存...", PREFIX + TICKET);
            if ((PREFIX + ACCESSTOKEN).equals(key)) {
                return WechatConfig.getWechatAccessToken(new HashMap<String, String>() {{
                    put(GRANTTYPE, grantType);
                    put(APPID, appid);
                    put(SECRET, appsecret);
                }});
            } else if ((PREFIX + TICKET).equals(key)) {
                return WechatConfig.getTicket(this.get(PREFIX + ACCESSTOKEN),ticketurl);
            }
            return null;
        });
    }
}