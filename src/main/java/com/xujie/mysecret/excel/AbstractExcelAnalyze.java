package com.xujie.mysecret.excel;

import java.io.InputStream;

public abstract class AbstractExcelAnalyze {

    /**
     * 解析excel
     *
     * @return
     */
    public abstract boolean analyzeExcel(InputStream inputStream);
}
