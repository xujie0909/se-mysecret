package com.xujie.mysecret.service;

import java.io.InputStream;

public interface BillService {

    /**
     * 处理微信账单
     */
    void dealWechatBill(InputStream inputStream);

    /**
     * 处理支付宝账单
     */
    void dealAlipayBill(InputStream inputStream);
}
