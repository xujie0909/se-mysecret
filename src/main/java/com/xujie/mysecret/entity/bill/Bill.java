package com.xujie.mysecret.entity.bill;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 交易单号
     */
    private String transid;

    /**
     * 商户单号
     */
    private String businessTransId;

    /**
     * 账单类型：收 1、支 0
     */
    private int billType;

    enum BillType {
        IN(1, "收入"),
        OUT(0, "支出");
        private String type;
        private int desc;

        BillType(int desc, String type) {
            this.type = type;
            this.desc = desc;
        }
    }

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 交易对方名称
     */
    private String businessName;

    /**
     * 交易时间
     */
    private Date date;

    private int transType;

    //改成字典表
    enum TransType{

        //支付宝 0**
        JISHIJIAOYI(001,"支付宝即时交易类型"),
        DANBAOJIAOYI(002,"支付宝担保交易"),

        //微信 1**
        WEIXINHONGBAO(101,"微信红包"),
        WEIXINHONGBAOback(102,"微信红包-退款"),
        XIAOFEI(103,"商户消费"),
        ERWEIMASHOUKUAN(104,"二维码收款"),
        LINGQIANCHONGZHI(105,"零钱充值"),
        QUNARWANGTUIKUAN(106,"去哪儿网退款"),
        QUNSHOUKUAN(107,"群收款"),
        ZANSHANGMA(108,"赞赏码"),
        ZHUANZHANG(109,"转账"),
        ZHUANZHUANTUIKUAN(109,"转账"),




        ;
        private int type;
        private String desc;

        TransType(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }
    }







}
