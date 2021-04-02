package com.example.learn.liststudent.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yichuan
 */
public class Utils {


    public static String getStringFormatDate(Date date) {
        String stringDate = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stringDate = simpleDateFormat.format(date);
        return stringDate;
    }

    /**
     * 获取内置ID
     *
     * @return
     */
    public static String getInnerId(String prefix) {
        return prefix + SnowflakeUtil.getInstance().nextId();

    }


    /**
     * 检查文件是否存在
     *
     * @return
     * @throws Exception
     */
    public static boolean checkFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        System.out.println("请先录入数据!");
        return false;
    }

}
