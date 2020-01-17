package com.xujie.mysecret.service;

import com.xujie.mysecret.entity.mark.MarkContent;

import java.util.List;

/**
 * @author xujie17
 */
public interface MarkContentService {

    /**
     * 增
     * @param markContent 实体
     */
    void saveOrUpdate(MarkContent markContent);

    /**
     * 根据id删除
     * @param id 实体ID
     */
    void delete(Long id);

    /**
     * 获取全部
     * @return 实体列表
     */
    List<MarkContent> findAll();
}
