package com.xujie.mysecret.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author xujie
 */
public class BillReader {

    public static List<CsvRow> readBillFileToList(String file, Charset charset) {
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(file), charset);
        return data.getRows();
    }

    public static void readAlipayBillListToEntity(List<CsvRow> rows) {
        int startRow = 5;
        for (int i = startRow; i < rows.size() - 7; i++) {
            System.out.println(rows.get(i));
        }
    }

    public static void readWechatBillListToEntity(List<CsvRow> rows) {
        int startRow = 17;
        for (int i = startRow; i < rows.size(); i++) {
            System.out.println(rows.get(i));
        }
    }

    public static void main(String[] args) {
        List<CsvRow> alipayBill = readBillFileToList("C:\\Users\\xujie\\Desktop\\alipay_record_20200504_2323_1.csv", Charset.forName("GBK"));
        List<CsvRow> wechatBill = readBillFileToList("C:\\Users\\xujie\\Desktop\\微信支付账单(20200301-20200331).csv", StandardCharsets.UTF_8);
        readAlipayBillListToEntity(alipayBill);
        readWechatBillListToEntity(wechatBill);

    }
}
