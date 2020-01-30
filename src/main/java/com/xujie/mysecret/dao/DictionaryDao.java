package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryDao extends JpaRepository<Dictionary,Long> {

}
