package com.xujie.mysecret.service.bill;

import com.xujie.mysecret.entity.bill.Bill;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public interface BillService {

    /**
     * 解析微信账单
     */
    void dealWechatBill(InputStream inputStream);

    /**
     * 解析支付宝账单
     */
    void dealAlipayBill(InputStream inputStream);

    /**
     * 查询账单列表
     */
    List<Bill> list(Bill bill);

    /**
     * 查询账单来源占比
     */
    HashMap<String,Object> sourceProportion();
}
