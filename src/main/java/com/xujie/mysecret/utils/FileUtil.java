package com.xujie.mysecret.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtil {

    /**
     * 从文件中读取字符串，返回字符串
     * @param fileName 文件路径
     * @return 字符串
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            log.info("readFile error:",e);
            return null;
        }
    }
}
