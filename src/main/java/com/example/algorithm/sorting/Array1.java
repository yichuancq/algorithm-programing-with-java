package com.example.algorithm.sorting;

/**
 * 线性存储的随机数集合
 *
 * @author yichuan
 */
public class Array1 {
    /**
     * 输出对象数组元素，静态方法。时间复杂度为O(n)
     *
     * @param value
     */
    public static void print(Object[] value) {
        //逐元循环，obj逐个引用value数组元素，次序同数组
        for (Object obj : value) {
            System.out.print(obj == null ? "null " : " " + obj.toString());
        }
        System.out.println();
    }

    /***
     *产生n个随机数（可重复），范围是0～size-1，返回整数对象数组
     */
    public static Integer[] randomInteger(int n, int size) {
        Integer[] values = new Integer[n];
        //java.lang.Integer是int类型的包装类
        for (int i = 0; i < values.length; i++)
        //遍历数组，访问每个元素仅一次
        {
            //Java自动将int整数封装成Integer对象，赋值相容
            values[i] = (int) (Math.random() * size);
        }
        //java.lang.Math.random()方法产生一个0～1之间double类型的随机数
        return values;
    }

    /***
     *  8.2 二分法查找； 9.1~9.4 排序
     *  输出数组元素
     */
    public static void print(int[] value) {
        for (int i = 0; i < value.length; i++) {
            System.out.print("  " + value[i]);
        }
        System.out.println();
    }

    /**
     * /9.1~9.4 排序产生n个随机数，返回整型数组
     *
     * @param n
     * @param size
     * @return
     */
    public static int[] randomInt(int n, int size) {
        int value[] = new int[n];
        for (int i = 0; i < value.length; i++) {
            //产生一个0～size-1之间的随机数
            value[i] = (int) (Math.random() * size);
        }
        //返回一个数组
        return value;
    }

    /**
     * 没有用到
     * 返回数组元素，方法体省略
     *
     * @param value
     * @return
     */
    public static String toString(int[] value) {
        String str = "";
        for (int i = 0; i < value.length; i++)
            str += " " + value[i];
        return str;
    }
}
