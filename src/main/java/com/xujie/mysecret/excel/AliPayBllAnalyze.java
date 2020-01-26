package com.xujie.mysecret.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

@Slf4j
public class AliPayBllAnalyze extends AbstractExcelAnalyze {

    @Override
    public boolean analyzeExcel(String fileName) {
        File file = new File(fileName);
        if(!file.exists()){
            log.info("账单文件不存在，请检查文件");
        }

        EasyExcel.read(fileName, new Listener()).sheet().headRowNumber(4).doRead();

        return true;
    }

    class Listener extends AnalysisEventListener<Map<Integer, String>>{

        @Override
        public void invoke(Map<Integer, String> data, AnalysisContext context) {
            
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {

        }
    }
}
