package com.xujie.mysecret.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xujie
 */
@Data
@Entity
@ToString
@Table(name = "sys_dictionary")
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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
