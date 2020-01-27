package com.xujie.mysecret.service;

import com.xujie.mysecret.entity.Dictionary;

import java.util.List;

public interface DictionaryService {

    Dictionary save(Dictionary dictionary);

    void delete(Long id);

    Dictionary update(Dictionary dictionary);

    List<Dictionary> findAll();

}
