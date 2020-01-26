package com.xujie.mysecret.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Dictionary {

    /**
     * 唯一id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 字典类型
     */
    private String dicType;

    /**
     * 字典名称
     */
    private String dicName;

    /**
     * 字典值
     */
    private String dicValue;

    /**
     * 字典描述
     */
    private String dicDesc;
}
