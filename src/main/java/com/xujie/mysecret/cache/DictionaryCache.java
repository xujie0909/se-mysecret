package com.xujie.mysecret.cache;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.service.DictionaryService;
import com.xujie.mysecret.service.impl.DictionaryServiceImpl;
import com.xujie.mysecret.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Cache;
import javax.persistence.PrePersist;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.xujie.mysecret.common.Constant.*;

/**
 * @author xujie17
 */
/*@Configuration
@EnableScheduling*/
@Component
@Slf4j
public class DictionaryCache {

    private DictionaryService dictionaryService;

    private static final ConcurrentHashMap<String,Object> CACHE = new ConcurrentHashMap<>();

    @Lazy
    public DictionaryCache(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /*@Scheduled(cron = "0/5 * * * * *")*/
    @PostConstruct
    public void loadCache(){
        log.info("刷新缓存...");
        List<Dictionary> allDict = dictionaryService.findAll();
        log.info("获取的缓存数量为{},字典项为:{}",allDict.size(),allDict);
        Map<String, List<Dictionary>> allDicGroup =
                allDict.stream().collect(Collectors.groupingBy(Dictionary::getDicType));
        CACHE.putAll(allDicGroup);
    }

    @SuppressWarnings("all")
    public Dictionary getDictByTypeAndName(String type,String name){
        if(StringUtils.isBlank(type) || StringUtils.isBlank(name)){
            return null;
        }
        List<Dictionary> allDic = (List<Dictionary>) CACHE.get(type);
        for (Dictionary dictionary : allDic) {
            if(name.equals(dictionary.getDicName())){
                return dictionary;
            }
        }
        return null;
    }

    @SuppressWarnings("all")
    public Dictionary getDicByName(String name){
        if( StringUtils.isBlank(name)){
            return null;
        }

        Collection<Object> dictLists = CACHE.values();
        for (Object dictList : dictLists) {
            List<Dictionary> dictionaryList = (List<Dictionary>) dictList;
            for (Dictionary dictionary : dictionaryList) {
                if(name.equals(dictionary.getDicName())){
                    return dictionary;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("all")
    public List<Dictionary> getDictsByType(String type){
        if( StringUtils.isBlank(type)){
            return null;
        }

        return (List<Dictionary>) CACHE.get(type);
    }
}