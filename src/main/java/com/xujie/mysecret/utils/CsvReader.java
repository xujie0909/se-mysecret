package com.xujie.mysecret.utils;

import java.io.*;
import java.util.ArrayList;

public class CsvReader {
    // 文件路径
    private String filePath;
    private File file;
    private DataInputStream in;
    private BufferedReader br;

    public CsvReader(String filepath) {
        this.filePath = filepath;
    }

    /**
     * 读取标题
     *
     * @return 以字符串数组存储的一行数据
     */
    public String[] ReadTitle() {
        String[] titleItems;
        try {
            file = new File(filePath);
            in = new DataInputStream(new FileInputStream(file));
            // CSV默认为GBK编码格式
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
            // 独处的结果为String
            String titleLine = br.readLine();
            // 用字符串数组存储此行不同列数据
            titleItems = titleLine.split(",");
            // 关掉是个好习惯
            br.close();
            return titleItems;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 按行号读取数据
     *
     * @param index
     *            行号
     * @return 以字符串数组存储的一行数据
     */
    public String[] ReadByLineIndex(int index) {
        String[] resultItems = null;
        String resultLine = null;
        try {
            file = new File(filePath);
            in = new DataInputStream(new FileInputStream(file));
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
            // 遍历文件，直到找到那一行
            for (int i = 0; i < index; i++) {
                resultLine = br.readLine();
            }
            if (resultLine != null) {
                resultItems = resultLine.split(",");
            }
            br.close();
            return resultItems;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 按列号读取数据,包括标题
     *
     * @param index
     *            列号
     * @return 以字符串链表保存的数据，一个字符串为此列一行数据
     */
    public ArrayList<String> ReadByColumnIndex(int index) {
        ArrayList<String> resultItems = new ArrayList<String>();
        String[] lineItem;
        String line;
        try {
            file = new File(filePath);
            in = new DataInputStream(new FileInputStream(file));
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
            // 遍历每一行，提取每一行对应列的数据
            while ((line = br.readLine()) != null) {
                lineItem = line.split(",");
                if (lineItem.length != 0) {
                    resultItems.add(lineItem[index - 1]);
                } else {
                    resultItems.add(null);
                }
            }
            br.close();
            return resultItems;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 按列号读取数据，不包括标题
     *
     * @param index
     * @return 以字符串链表保存的数据，一个字符串为此列一行数据
     */
    public ArrayList<String> ReadDataByColumnIndex(int index) {
        ArrayList<String> result = this.ReadByColumnIndex(index);
        if (result != null) {
            result.remove(0);
            return result;
        } else {
            return null;
        }

    }

    /**
     * 读取所有数据，包括标题
     *
     * @return 以字符串数组链表保存的数据，一个字符串数组为一行数据
     */
    public ArrayList<String[]> ReadAll() {
        ArrayList<String[]> result = new ArrayList<String[]>();
        String[] lineItem;
        String line;
        try {
            file = new File(filePath);
            in = new DataInputStream(new FileInputStream(file));
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
            // 遍历读取
            while ((line = br.readLine()) != null) {
                lineItem = line.split(",");
                result.add(lineItem);
            }
            br.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 读取所有数据，包括标题
     *
     * @return 以字符串数组链表保存的数据，一个字符串数组为一行数据
     */
    public ArrayList<String[]> ReadAll(InputStream inputStream,String charsetName) {
        ArrayList<String[]> result = new ArrayList<>();
        String[] lineItem;
        String line;
        try {
            //file = new File(filePath);
            in = new DataInputStream(inputStream);
            br = new BufferedReader(new InputStreamReader(in, charsetName));
            // 遍历读取
            while ((line = br.readLine()) != null) {
                lineItem = line.split(",");
                result.add(lineItem);
            }
            br.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
