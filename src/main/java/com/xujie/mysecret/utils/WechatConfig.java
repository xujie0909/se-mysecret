package com.xujie.mysecret.utils;

import com.alibaba.fastjson.JSONObject;
import com.xujie.mysecret.common.Constant;

import java.util.HashMap;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
public class WechatConfig {

    private static final String GRANT_TYPE = "grant_type";
    private static final String APPID = "appid";
    private static final String SECRET = "secret";
    private static final String TYPE = "type";
    private static final String JSAPI = "jsapi";

    /**
     * 获取token
     * @return token
     */
    public static String getWechatAccessToken() {
        String tokenResult = HttpUtil.doGet(TOKENURL, new HashMap<String, String>(16) {{
            put(GRANT_TYPE, GRANTTYPE);
            put(APPID, Constant.APPIDVALUE);
            put(SECRET, APPSECRET);
        }});
        return JSONObject.parseObject(tokenResult).get(ACCESSTOKEN).toString();
    }

    /**
     * 获取ticket
     * @param token token
     * @return ticket
     */
    public static String getTicket(String token){

        String ticketResult = HttpUtil.doGet(TICKETURL, new HashMap<String,String>(){{
            put(ACCESSTOKEN,token);
            put(TYPE,JSAPI);
        }});

        JSONObject ticketObject = JSONObject.parseObject(ticketResult);
        if(ticketObject == null){
            return null;
        }
        return (String)ticketObject.get(TICKET);
    }

}
