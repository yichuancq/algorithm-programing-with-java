package com.example.leetcode.findrelativeranks;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
 * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
 *
 */
public class FindRelativeRanks {

    public String[] findRelativeRanks(int[] score) {
        HashMap hashMap = new HashMap();
        String goldString[] = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        int temp[] = Arrays.copyOf(score, score.length);
        String resultString[] = new String[score.length];
        if (score == null || score.length == 0) {
            return resultString;
        }
        if (score.length == 1) {
            resultString[0] = goldString[0];
            return resultString;
        }
        Arrays.sort(score);
        int j = score.length;
        for (int i = 0; i < score.length; i++) {
            Integer key = score[i];
            Integer val = j--;
            hashMap.put(key, val);
        }
        for (int i = 0; i < temp.length; i++) {
            Integer key = temp[i];
            if (hashMap.get(key).equals(1)) {
                resultString[i] = goldString[0];
            } else if (hashMap.get(key).equals(2)) {
                resultString[i] = goldString[1];
            } else if (hashMap.get(key).equals(3)) {
                resultString[i] = goldString[2];
            } else {
                resultString[i] = String.valueOf(hashMap.get(key));
            }
        }
        return resultString;
    }

    public static void main(String[] args) {
        FindRelativeRanks findRelativeRanks = new FindRelativeRanks();
        int nums[] = {5, 4, 3, 2, 1};
        String resultString[] = findRelativeRanks.findRelativeRanks(nums);
        //[4, 5, 6, 7, Silver Medal, Bronze Medal, Gold Medal]
        System.out.println(Arrays.asList(resultString));
    }

}
