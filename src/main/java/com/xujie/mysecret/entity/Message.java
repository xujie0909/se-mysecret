package com.xujie.mysecret.entity;

import lombok.Data;

@Data
public class Message {
    public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;

    private String message;

    private Integer status;
}
