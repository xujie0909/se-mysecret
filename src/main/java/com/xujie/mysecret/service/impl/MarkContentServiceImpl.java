package com.xujie.mysecret.service.impl;

import com.xujie.mysecret.dao.MarkContentDao;
import com.xujie.mysecret.entity.MarkContent;
import com.xujie.mysecret.service.MarkContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xujie17
 */
@Service
@Slf4j
public class MarkContentServiceImpl implements MarkContentService {

    private final MarkContentDao markContentDao;

    public MarkContentServiceImpl(MarkContentDao markContentDao) {
        this.markContentDao = markContentDao;
    }

    @Override
    public void saveOrUpdate(MarkContent markContent) {
        MarkContent saveResult = markContentDao.save(markContent);
        log.info("存储成功：{}",saveResult);
    }

    @Override
    public void delete(Long id) {
        markContentDao.deleteById(id);
        log.info("删除成功！");

    }

    @Override
    public List<MarkContent> findAll() {
        return null;
    }
}
