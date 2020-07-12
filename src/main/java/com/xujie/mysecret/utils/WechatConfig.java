package com.xujie.mysecret.utils;

import com.alibaba.fastjson.JSONObject;
import com.xujie.mysecret.common.Constant;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
public class WechatConfig {

    @Value("my.secret.wechat.appid")
    private String appid;

    @Value("my.secret.wechat.appsecret")
    private String appsecret;

    private static final String GRANT_TYPE = "grant_type";
    private static final String APPID = "appid";
    private static final String SECRET = "secret";
    private static final String TYPE = "type";
    private static final String JSAPI = "jsapi";

    /**
     * 获取token
     *
     * @return token
     */
    public static String getWechatAccessToken(HashMap<String, String> param) {

        String tokenResult = HttpUtil.doGet(TOKENURL, param);

        return JSONObject.parseObject(tokenResult).get(ACCESSTOKEN).toString();
    }

    /**
     * 获取ticket
     *
     * @param token token
     * @return ticket
     */
    public static String getTicket(String token,String ticketurl) {

        String ticketResult = HttpUtil.doGet(ticketurl, new HashMap<String, String>() {{
            put(ACCESSTOKEN, token);
            put(TYPE, JSAPI);
        }});

        JSONObject ticketObject = JSONObject.parseObject(ticketResult);
        if (ticketObject == null) {
            return null;
        }
        return (String) ticketObject.get(TICKET);
    }

}
