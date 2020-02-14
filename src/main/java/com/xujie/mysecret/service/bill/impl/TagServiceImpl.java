package com.xujie.mysecret.service.bill.impl;

import com.xujie.mysecret.dao.TagDao;
import com.xujie.mysecret.entity.bill.Tag;
import com.xujie.mysecret.service.bill.TagService;
import com.xujie.mysecret.utils.Base64Util;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.xujie.mysecret.common.Constant.TIMEFORMAT;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag saveTag(Tag tag) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIMEFORMAT);
        tag.setDate(simpleDateFormat.format(new Date()));

        tag.setTid(Base64Util.encode(tag.getTagName()));

        return this.tagDao.save(tag);
    }

    @Override
    public List<Tag> list(Tag tag) {
        return tagDao.findAll();
    }
}
