package com.xujie.mysecret.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DictionaryMapper {

    /**
     * 查询所有的字典类型
     * @return 字典类型
     */
    List<String> findAlldicTypes();
}
