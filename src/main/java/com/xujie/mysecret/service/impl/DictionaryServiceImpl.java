package com.xujie.mysecret.service.impl;

import com.xujie.mysecret.cache.DictionaryCache;
import com.xujie.mysecret.common.Constant;
import com.xujie.mysecret.dao.DictionaryDao;
import com.xujie.mysecret.dao.mapper.DictionaryMapper;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    private final DictionaryMapper dictionaryMapper;

    private final DictionaryCache dictionaryCache;

    public DictionaryServiceImpl(DictionaryDao dictionaryDao, DictionaryMapper dictionaryMapper, DictionaryCache dictionaryCache) {
        this.dictionaryDao = dictionaryDao;
        this.dictionaryMapper = dictionaryMapper;
        this.dictionaryCache = dictionaryCache;
    }

    @Override
    public Dictionary save(Dictionary dictionary) {
        dictionary.setCreateTime(new SimpleDateFormat(Constant.TIMEFORMAT).format(new Date()));
        dictionary.setUpdateTime(dictionary.getCreateTime());
        dictionaryCache.loadCache();
        return dictionaryDao.save(dictionary);
    }

    @Override
    public void delete(Long id) {
        dictionaryCache.loadCache();
        this.dictionaryDao.deleteById(id);
    }

    @Override
    public Dictionary update(Dictionary dictionary) {
        dictionaryCache.loadCache();
        dictionary.setUpdateTime(new SimpleDateFormat(Constant.TIMEFORMAT).format(new Date()));
        return this.dictionaryDao.save(dictionary);
    }

    @Override
    public List<Dictionary> findAll() {
        return this.dictionaryDao.findAll(Sort.by(Sort.Direction.DESC, "id"));

    }

    @Override
    public List<String> getDicTypes() {
        List<String> alldicTypes = dictionaryMapper.findAlldicTypes();
        return alldicTypes;
    }
}
