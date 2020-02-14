package com.xujie.mysecret.service.bill;

import com.xujie.mysecret.entity.bill.Tag;

import java.util.List;

public interface TagService {

    /**
     * 保存标签
     * @param tag
     */
    Tag saveTag(Tag tag);

    /**
     * 标签列表
     */
    List<Tag> list(Tag tag);
}
