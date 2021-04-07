package com.example.algorithm.liststudent.utils;

import java.io.*;
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

    /**
     * 根据行内容删除某一指定行
     *
     * @param file             txt文件路径
     * @param lineKey          要删除的行Key
     * @param lineContent      要删除的行内容
     * @param retainRemoveLine 是否保留删除的行（保留：留下一个空白行，不保留：下面的行自动上移）
     * @throws Exception
     */
    public static void removeLineForLineContent(String file, String lineKey, String lineContent, boolean retainRemoveLine) throws Exception {
        //获取原文件
        File oldFile = new File(file);
        if (!oldFile.isFile()) {
            throw new Exception("该文件未找到:" + file);
        }
        //构造临时文件
        File newFile = new File(oldFile.getAbsolutePath() + ".tmp");
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter pw = new PrintWriter(new FileWriter(newFile));
        //某一行的值
        String line = null;
        while ((line = br.readLine()) != null) {
            //这里我用了equals，可以根据自己的需要改成index或者其他的
            if (line.contains(lineKey) && !line.contains(lineContent)) {
                pw.println(line);
                pw.flush();
            } else {
                //保留删除行
                if (retainRemoveLine) {
                    pw.println("");
                }
                pw.flush();
            }
        }
        pw.close();
        br.close();
        //删除原文件
        if (!oldFile.delete()) {
            throw new Exception("该文件删除失败:" + file);
        }
        //重命名
        if (!newFile.renameTo(oldFile)) {
            throw new Exception("该文件重命名失败:" + file);
        }
    }
}
