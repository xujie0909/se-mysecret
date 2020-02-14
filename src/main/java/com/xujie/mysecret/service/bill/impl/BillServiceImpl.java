package com.xujie.mysecret.service.bill.impl;

import com.xujie.mysecret.cache.DictionaryCache;
import com.xujie.mysecret.dao.BillDao;
import com.xujie.mysecret.dao.mapper.BillMapper;
import com.xujie.mysecret.entity.Dictionary;
import com.xujie.mysecret.entity.bill.Bill;
import com.xujie.mysecret.service.bill.BillService;
import com.xujie.mysecret.service.bill.TagService;
import com.xujie.mysecret.utils.CsvReader;
import com.xujie.mysecret.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.xujie.mysecret.common.Constant.TIMEFORMAT;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    private final BillDao billDao;
    private final BillMapper billMapper;
    private final TagService tagService;
    private final DictionaryCache dictionaryCache;

    @Lazy
    public BillServiceImpl(BillDao billDao, BillMapper billMapper, TagService tagService, DictionaryCache dictionaryCache) {
        this.billDao = billDao;
        this.billMapper = billMapper;
        this.tagService = tagService;
        this.dictionaryCache = dictionaryCache;
    }


    @Override
    public void dealWechatBill(InputStream inputStream) {

        CsvReader csvReader = new CsvReader("");
        ArrayList<String[]> rowsData = csvReader.ReadAll(inputStream, "UTF8");
        ArrayList<Bill> bills = new ArrayList<>();

        Dictionary dict = dictionaryCache.getDicByTypeAndName("systemConfig", "weChatBillStartRow");
        int startRow = Integer.parseInt(dict.getDicValue());
        log.info("微信起始行为:{}",startRow);

        for (int i = startRow; i < rowsData.size(); i++) {
            String[] row = rowsData.get(i);
            for (int j = 0; j < row.length; j++) {
                row[j] = row[j].trim();
            }
            if (StringUtils.isBlank(row[0])) {
                break;
            }
            Bill bill = new Bill();
            SimpleDateFormat formater = new SimpleDateFormat(TIMEFORMAT);
            try {
                bill.setDate(String.valueOf(formater.parse(row[0]).getTime()));
            } catch (ParseException e) {
                log.error("账单日期解析失败！日期:{}", preHandle(row[0]), e);
                return;
            }

            bill.setTransType(row[1]);
            bill.setBusinessName(row[2]);
            bill.setProductName(row[3]);

            if ("/".equals(row[4])) {
                setBillType(row, bill);
            } else if (row[4].equals("收入")) {
                bill.setBillType("100");
            } else if (row[4].equals("支出")) {
                bill.setBillType("001");
            }

            try {
                bill.setAmount(new BigDecimal(row[5].replace("¥", "")));
            } catch (Exception e) {
                log.error("金额解析出错！金额为:{}", row[5]);
                return;
            }
            bill.setPayType(row[6]);
            bill.setTransid(row[8]);
            bill.setBillSource("微信");
            bill.setBusinessTransId(row[9]);
            bill.setStatus("1");
            bill.setTranStatus(row[7]);
            bills.add(bill);
        }

        List<Bill> saveRresult = billDao.saveAll(bills);
        log.info("存储结果:{}", saveRresult.size());

    }

    @Override
    public void dealAlipayBill(InputStream inputStream) {

        CsvReader csvReader = new CsvReader("");
        ArrayList<String[]> rowsData = csvReader.ReadAll(inputStream, "GBK");

        Dictionary dict = dictionaryCache.getDicByTypeAndName("systemConfig", "alipayBillStartRow");
        int startRow = Integer.parseInt(dict.getDicValue());
        log.info("支付宝账单起始行为:{}",startRow);
        for (int i = startRow; i < rowsData.size(); i++) {

            String[] row = rowsData.get(i);
            for (int j = 0; j < row.length; j++) {
                row[j] = row[j].trim();
            }
            if (row[0].startsWith("---------")) {
                break;
            }
            //处理数据
            Bill bill = new Bill();
            SimpleDateFormat formater = new SimpleDateFormat(TIMEFORMAT);

            try {
                if (StringUtils.isBlank(preHandle(row[2]))) {
                    bill.setDate(String.valueOf(formater.parse("1970-01-01 00:00:00").getTime()));
                } else {
                    bill.setDate(String.valueOf(formater.parse(preHandle(row[2])).getTime()));
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
            bill.setTransid(preHandle(row[0]));
            bill.setBillSource("支付宝");
            bill.setBusinessTransId(preHandle(row[1]));
            bill.setBusinessName(preHandle(row[7]));
            bill.setProductName(preHandle(row[8]));

            //账单类型：收/支
            if (StringUtils.isBlank(row[10])) {
                setBillType(row, bill);
            } else {
                bill.setBillType("支出".equals(preHandle(row[10])) ? "001" : "002");
            }

            bill.setPayType("支付宝");
            bill.setTranStatus(preHandle(row[11]));
            bill.setStatus("1");
            billDao.save(bill);
        }
    }

    @Override
    public List<Bill> list(Bill bill) {

        String billType = StringUtils.isBlank(bill.getBillType()) ? "" : bill.getBillType();
        if (billType.equals("收入")) {
            bill.setBillType("1");
        } else if (billType.equals("支出")) {
            bill.setBillType("0");
        }

        List<Bill> list = billMapper.findByBill(bill);

        for (Bill b : list) {
            if (b.getStatus().equals(Bill.statusEnum.DONE.getType())) {
                b.setStatus(Bill.statusEnum.DONE.getDesc());
            }

            SimpleDateFormat formater = new SimpleDateFormat(TIMEFORMAT);
            Date date = new Date(Long.parseLong(b.getDate()));
            b.setDate(formater.format(date));

            if (b.getBillType().startsWith("1")) {
                b.setBillType("收入");
            } else if (b.getBillType().startsWith("0")) {
                b.setBillType("支出");
            }
        }
        return list;
    }

    @Override
    public HashMap<String, Object> sourceProportion() {
        List<Map<String, Object>> maps = this.billMapper.sourceProportion();
        HashMap<String, Object> resultMap = new HashMap<>();

        ArrayList<String> sourceTypeList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            sourceTypeList.add((String)map.get("name"));
        }
        resultMap.put("sourceType", sourceTypeList);
        resultMap.put("typeAndValue", maps);
        return resultMap;
    }

    /**
     * 当账单收支类型为空时，根据其他字段信息补充收支类型
     *
     * @param data 一条账单信息
     * @param bill 账单对象
     */
    public void setBillType(String[] data, Bill bill) {

        if ("零钱充值".equals(data[1])) {
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

        if ("余额宝-转出到余额".equals(data[8])) {
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
            return;
        }

        if ("定期理财-建信养老飞月宝".equals(data[8])) {
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

    //public void

}
