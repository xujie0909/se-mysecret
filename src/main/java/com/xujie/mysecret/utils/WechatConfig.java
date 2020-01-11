package com.xujie.mysecret.utils;

import com.alibaba.fastjson.JSONObject;
import com.xujie.mysecret.common.Constant;

import java.util.HashMap;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
public class WechatConfig {

    public static String getWechatAccessToken(){
        //获取token
        String tokenResult = HttpUtil.doGet(TOKENURL, new HashMap<String, String>(16) {{
            put("grant_type", GRANTTYPE);
            put("appid", Constant.APPID);
            put("secret", APPSECRET);
        }});
        return JSONObject.parseObject(tokenResult).get(ACCESSTOKEN).toString();
    }
}
