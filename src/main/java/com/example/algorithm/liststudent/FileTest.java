package com.example.algorithm.liststudent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.algorithm.liststudent.base.Student;
import org.junit.Test;

import java.io.*;

public class FileTest {
    /**
     * 保存文件的路径
     */
    public final String studentFilePath = "src/main/resources/test.txt";

    /**
     * 根据行内容删除某一指定行
     *
     * @param file             txt文件路径
     * @param lineContent      要删除的行内容
     * @param retainRemoveLine 是否保留删除的行（保留：留下一个空白行，不保留：下面的行自动上移）
     */
    public static void removeLineForLineContent(String file, String lineContent, boolean retainRemoveLine) throws Exception {

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
            if (!line.trim().equals(lineContent)) {
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


    @Test
    public void testWriteLine() throws Exception {
        int size = 10;
        FileWriter fileWriter = new FileWriter(studentFilePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //  for (int i = 0; i < size; i++) {
        Student student = new Student("aa", "sss");
        //  Student student = new Student("Stu" + i, "name" + i);
        String line = JSON.toJSONString(student);
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        //  }
        bufferedWriter.flush(); //将数据更新至文件
        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * @throws Exception
     */
    @Test
    public void testReadLine() throws Exception {
        FileReader fileReader = new FileReader(studentFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String content = null;
        //按行读取
        while ((content = bufferedReader.readLine()) != null) {
            //判断为空
            if (!content.isEmpty()) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                // 将JSONObject对象转为StockMesBean实体对象
                Student student = JSON.toJavaObject(jsonObject, Student.class);
                System.out.println(student.getName());
            }

        }
        fileReader.close();
    }

    @Test
    public void testRemove() throws Exception {
        removeLineForLineContent(studentFilePath, "{\"createTime\":1617805364432,\"name\":\"sss\",\"number\":\"aa\",\"studentCourses\":[],\"updateTime\":1617805364432}", false);
        System.out.println();

    }
}
