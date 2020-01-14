package com.xujie.mysecret.entity;

import lombok.Data;

/**
 * @author xujie17
 */
@Data
public class WxResponse {

    /**
     * 响应码
     */
    private int statusCode;

    /**
     * 状态描述
     */
    private String statusDes;

    /**
     * 请求结果
     */
    private Object resResult;


}
