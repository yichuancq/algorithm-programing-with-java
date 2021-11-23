package com.example.leetcode.average;

import java.math.BigDecimal;
import java.util.*;

/**
 * 你一个整数数组salary，数组里每个数都是 唯一的，其中salary[i] 是第i个员工的工资。
 * <p>
 * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
 * <p>
 * 输入：salary = [4000,3000,1000,2000]
 * 输出：2500.00000
 * 解释：最低工资和最高工资分别是 1000 和 4000 。
 * 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
 */
public class Solution {

    /**
     * @param salary
     * @return
     */
    public double average(int[] salary) {
        if (salary == null || salary.length == 0) {
            return 0.0d;
        }
        List<Integer> integerList = calc(salary);
        BigDecimal i = new BigDecimal(0);
        for (Integer temp : integerList) {
            BigDecimal bigDecimal = BigDecimal.valueOf(temp);
            i = i.add(bigDecimal);
        }
        return i.doubleValue() / integerList.size();
    }

    private List<Integer> calc(int[] salary) {
        List<Integer> integerList = new ArrayList<>();
        int[] arrays = new int[2];
        int min = salary[0];
        int max = salary[0];
        for (int i = 0; i < salary.length; i++) {
            int temp = salary[i];
            integerList.add(temp);
            if (min >= temp || max <= temp) {
                min = Math.min(min, temp);
                max = Math.max(max, temp);
            }
        }
        arrays[0] = min;
        arrays[1] = max;
        Iterator iterator = integerList.listIterator();
        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            if (integer == min || integer == max) {
                iterator.remove();
            }
        }
        System.out.println(String.format("最小:%d,最大 %d", min, max));
        //Collections.sort(integerList);
        System.out.println(integerList);
        return integerList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int salary[] = {48000, 59000, 99000, 13000, 78000, 45000, 31000, 17000, 39000,
                37000, 93000, 77000, 33000, 28000, 4000, 54000, 67000, 6000, 1000, 11000};
        double result = solution.average(salary);
        System.out.println(result);
    }
}
