package com.example.algorithm.liststudent;

import com.example.algorithm.liststudent.service.BaseService;
import com.example.algorithm.liststudent.service.StudentService;

/**
 * @author yichuan
 */
public class MainFunction {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //基础服务类
            BaseService baseService = new BaseService();
            //
            StudentService studentTest = new StudentService(baseService);
            //
            studentTest.initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
