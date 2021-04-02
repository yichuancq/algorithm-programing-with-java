package com.example.learn.liststudent.utils;

import java.io.File;

/**
 * @author yichuan
 */
public class Utils {

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
