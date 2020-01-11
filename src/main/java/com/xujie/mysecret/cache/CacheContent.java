package com.xujie.mysecret.cache;

import com.google.common.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xujie17
 */
@Component
public class CacheContent implements ICache<String,String> {

    private Cache<String,String> cache;

    @Override
    public void save(String key, String value) {
        CacheManager.CACHE.put(key,value);
    }

    @Override
    public String get(String key) {
        //return CacheManager.CACHE.get(key,);
        return null;
    }
}
