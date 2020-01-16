package com.xujie.mysecret.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xujie17
 */
@Data
@Entity
public class MarkContent {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    private String mcName;

    /**
     * 描述
     */
    private String mcDesc;
}
