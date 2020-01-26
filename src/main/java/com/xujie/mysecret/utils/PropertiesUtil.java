package com.xujie.mysecret.utils;

import com.xujie.mysecret.common.Constant;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class PropertiesUtil {

    /**
     * 根据key获取对应的属性值
     * @param key 属性名称
     * @return 属性值
     * @throws Exception 异常
     */
    public static String getPropValueByName(String key) throws Exception{
        log.info("key is:{}",key);
        Properties prop = new Properties();
        prop.load(Object.class.getResourceAsStream(Constant.PROPPATH));
        return prop.getProperty(key);
    }
}
