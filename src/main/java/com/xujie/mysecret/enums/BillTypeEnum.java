package com.xujie.mysecret.enums;

import lombok.Data;

public enum BillTypeEnum {
    WEICHAT(1,"微信"),
    ALIPAY(2,"支付宝")
    ;
    private int type;
    private String name;

    BillTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

