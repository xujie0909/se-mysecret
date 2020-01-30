package com.xujie.mysecret.entity.bill;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Bill {

    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String billType;

    enum BillType {

        /**
         * 收入
         */
        INCOME("100"),
        /**
         * 红包奖励
         */
        HONGBAOJIANGLI("101"),
        /**
         * 普通充值
         */
        PUTONGCHONGZHI("102"),


        /**
         * 消费
         */
        COST("001"),
        /**
         * 转出到银行卡
         */
        OUTCARD("002"),
        /**
         * 花呗还款
         */
        HUABEI("003"),
        /**
         * 快速提现
         */
        TIXIAN("004"),


        /**
         * 交易关闭，未发生资金变动
         */
        NONE("000");

        private String type;

        BillType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

    /**
     * 交易类型
     */
    private String transType;

    /**
     * 支付方式
     */
    private String payType;

    /*enum PayType{
        LINGQIAN(1,"零钱"),
        ZHAOSHANGYINHANG(2,"招商银行(9219)")
        ;
        private int type;
        private String name;

        PayType(int type, String name) {
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
    }*/

    /**
     * 交易状态
     */
    private String tranStatus;

    /**
     * 单条账单处理状态
     */
    private String status;

    enum statusEnum{

        DONE("1")
        ;
        private String type;

        statusEnum(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


}
