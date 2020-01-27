package com.xujie.mysecret.service.impl;

import com.xujie.mysecret.dao.DictionaryDao;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    public Dictionary save(Dictionary dictionary) {
        return dictionaryDao.save(dictionary);
    }

    @Override
    public void delete(Long id) {
        this.dictionaryDao.deleteById(id);
    }

    @Override
    public Dictionary update(Dictionary dictionary) {
        return this.dictionaryDao.save(dictionary);
    }

    @Override
    public List<Dictionary> findAll() {
        return this.dictionaryDao.findAll();
    }
}
