package com.xujie.mysecret.entity.mark;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author xujie
 */
public class Progress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String itemId;

    private String childItem;

    private String countUnit;

    private String countValue;

    private Date createTime;

    private Date updateTime;
}
