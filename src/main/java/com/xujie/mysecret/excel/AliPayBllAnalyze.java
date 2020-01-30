package com.xujie.mysecret.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Slf4j
public class AliPayBllAnalyze extends AbstractExcelAnalyze {

    @Override
    public boolean analyzeExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, new Listener()).excelType(ExcelTypeEnum.XLSX).sheet().headRowNumber(4).doRead();
        return true;
    }

    static class Listener extends AnalysisEventListener<Map<Integer, String>>{

        @Override
        public void invoke(Map<Integer, String> data, AnalysisContext context) {
            log.info("=====>>>"+data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            log.info("全部解析完成!");
        }
    }
}
