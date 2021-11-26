package com.example.leetcode.findrelativeranks;


import java.util.Arrays;
import java.util.HashMap;

/**
 * 根据N名运动员的得分，找到他们的相对等级和获得最高分前三名的人，他们将获得奖牌：“金牌”，“银牌”和“铜牌”。
 * N是正整数，并且不超过10,000。
 * 所有运动员的成绩都保证是独一无二的。
 * <p>
 * 例子 1:
 * <p>
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 说明：前三名运动员获得前三名最高分，因此获得“金牌”，“银牌”和“铜牌”。
 * 对于后两名运动员，你只需要根据他们的分数输出他们的相对等级。
 */
public class Solution {
    /**
     * 考虑到数组顺序随机排列
     *
     * @param nums
     * @return
     */
    public String[] findRelativeRanks(int[] nums) {
        HashMap hashMap = new HashMap();
        String goldString[] = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        int temp[] = Arrays.copyOf(nums, nums.length);
        String resultString[] = new String[nums.length];
        if (nums == null || nums.length == 0) {
            return resultString;
        }
        if (nums.length == 1) {
            resultString[0] = goldString[0];
            return resultString;
        }
        Arrays.sort(nums);
        int j = nums.length;
        for (int i = 0; i < nums.length; i++) {
            Integer key = nums[i];
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

    /**
     * 261 ms时间消耗, 15.61 MB 空间消耗
     * 您的提交打败了91.00 的提交
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {5, 3, 2, 1, 8, 7, 10};
        String resultString[] = solution.findRelativeRanks(nums);
        //[4, 5, 6, 7, Silver Medal, Bronze Medal, Gold Medal]
        System.out.println(Arrays.asList(resultString));
    }
}
