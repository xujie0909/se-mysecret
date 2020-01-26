package com.xujie.mysecret.service.impl;

import com.xujie.mysecret.dao.DictionaryDao;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    public DictionaryImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }


    @Override
    public Dictionary save(Dictionary dictionary) {
        return dictionaryDao.save(dictionary);
    }

    @Override
    public void delete(Dictionary dictionary) {

    }

    @Override
    public void update(Dictionary dictionary) {

    }

    @Override
    public List<Dictionary> findAll() {
        return this.dictionaryDao.findAll();
    }
}
