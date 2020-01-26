package com.xujie.mysecret.entity.response;

import lombok.Data;

@Data
public class Response {

    /**
     *响应码
     */
    private int code;

    /**
     * 响应描述
     */
    private String message;

    /**
     * 响应体
     */
    private Object data;

}
