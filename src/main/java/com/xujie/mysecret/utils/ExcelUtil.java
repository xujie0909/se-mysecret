package com.xujie.mysecret.utils;

import com.xujie.mysecret.excel.AbstractExcelAnalyze;

public class ExcelUtil {

    public boolean readExcel(String fileName,String className)throws Exception{

        AbstractExcelAnalyze analyzer = (AbstractExcelAnalyze)Class.forName(className).newInstance();
        analyzer.analyzeExcel(fileName);



        return false;
    }
}
