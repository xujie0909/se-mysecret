package com.xujie.mysecret.service.impl;

import com.xujie.mysecret.dao.BillDao;
import com.xujie.mysecret.entity.bill.Bill;
import com.xujie.mysecret.service.BillService;
import com.xujie.mysecret.utils.CsvReader;
import com.xujie.mysecret.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BillServiceImpl implements BillService {


    private final BillDao billDao;

    public BillServiceImpl(BillDao billDao) {
        this.billDao = billDao;
    }


    @Override
    public void dealWechatBill(InputStream inputStream) {
        CsvReader csvReader = new CsvReader("");
        ArrayList<String[]> rowsData = csvReader.ReadAll(inputStream,"UTF8");
        ArrayList<Bill> bills = new ArrayList<>();
        for (int i = 18; i < rowsData.size(); i++) {
            String[] row = rowsData.get(i);
            for (int j = 0; j < row.length; j++) {
                row[j] = row[j].trim();
            }
            if(StringUtils.isBlank(row[0])){
                break;
            }
            Bill bill = new Bill();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                bill.setDate(formater.parse(row[0]));
            } catch (ParseException e) {
                log.error("账单日期解析失败！日期:{}", preHandle(row[0]), e);
                return;
            }

            bill.setTransType(row[1]);
            bill.setBusinessName(row[2]);
            bill.setProductName(row[3]);

            if("/".equals(row[4])){
                setBillType(row,bill);
            }else{
                bill.setBillType(row[4]);
            }

            try {
                bill.setAmount(new BigDecimal(row[5].replace("¥","")));
            } catch (Exception e) {
                log.error("金额解析出错！金额为:{}",row[5]);
                return;
            }
            bill.setPayType(row[6]);
            bill.setTransid("wechat_"+row[8]);
            bill.setBusinessTransId(row[9]);
            bill.setStatus("1");
            bill.setTranStatus(row[7]);
            bills.add(bill);

            //billDao.save(bill);
        }
        List<Bill> saveRresult = billDao.saveAll(bills);
        log.info("存储结果:{}",saveRresult.size());

    }

    @Override
    public void dealAlipayBill(InputStream inputStream) {

        CsvReader csvReader = new CsvReader("");
        ArrayList<String[]> rowsData = csvReader.ReadAll(inputStream,"GBK");
        for (int i = 5; i < rowsData.size(); i++) {

            String[] row = rowsData.get(i);
            for (int j = 0; j < row.length; j++) {
                row[j] = row[j].trim();
            }
            if(row[0].startsWith("---------")){
                break;
            }
            //处理数据
            Bill bill = new Bill();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                if (StringUtils.isBlank(preHandle(row[2]))) {
                    bill.setDate(formater.parse("1970-01-01 00:00:00"));
                } else {
                    bill.setDate(formater.parse(preHandle(row[2])));
                }
            } catch (ParseException e) {
                log.error("账单日期解析失败！日期:{}", preHandle(row[3]), e);
                return;
            }

            try {
                bill.setAmount(new BigDecimal(preHandle(row[9])));
            } catch (Exception e) {
                log.error("data is:{}", preHandle(row[9]), e);
                return;
            }

            bill.setTransType(preHandle(row[6]));
            bill.setTransid("alipay_"+preHandle(row[0]));
            bill.setBusinessTransId(preHandle(row[1]));
            bill.setBusinessName(preHandle(row[7]));
            bill.setProductName(preHandle(row[8]));

            //账单类型：收/支
            if (StringUtils.isBlank(row[10])) {
                setBillType(row, bill);
            } else {
                bill.setBillType("支出".equals(preHandle(row[10]))?"001":"002");
            }

            bill.setPayType("支付宝");
            bill.setTranStatus(preHandle(row[11]));
            bill.setStatus("1");
            billDao.save(bill);
        }
    }

    /**
     * 当账单收支类型为空时，根据其他字段信息补充收支类型
     *
     * @param data 一条账单信息
     * @param bill 账单对象
     */
    public void setBillType(String[] data, Bill bill) {

        if("零钱充值".equals(data[1])){
            bill.setBillType("102");
            return;
        }

        if ("交易关闭".equals(data[11])) {
            bill.setBillType("000");
            return;
        }

        if ("中欧基金管理有限公司".equals(data[7])
                && (data[8].equals("余额宝-单次转入") || data[8].equals("余额宝-自动转入"))) {
            bill.setBillType("000");
            return;
        }

        if("余额宝-转出到余额".equals(data[8])){
            bill.setBillType("000");
            return;
        }

        if ("余额宝-转出到银行卡".equals(data[8])) {
            bill.setBillType("002");
            return;
        }

        if ("花呗".equals(data[7]) && data[8].startsWith("主动还款")) {
            bill.setBillType("003");
            return;
        }

        if ("中欧基金管理有限公司".equals(data[7]) && "红包奖励发放".equals(data[8])) {
            bill.setBillType("101");
            return;
        }

        if ("充值-普通充值".equals(data[8])) {
            bill.setBillType("102");
            return;
        }

        if ("提现-快速提现".equals(data[8])) {
            bill.setBillType("004");
            return ;
        }

        if("定期理财-建信养老飞月宝".equals(data[8])){
            bill.setBillType("000");
            return;
        }
    }

    /**
     * 去除字段空格
     *
     * @param data 需要处理的字段
     * @return 处理之后的字段
     */
    public String preHandle(String data) {
        if (StringUtils.isBlank(data)) {
            return "";
        }
        return data.trim();
    }
}
