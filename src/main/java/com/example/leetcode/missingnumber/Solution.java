package com.example.leetcode.missingnumber;

public class Solution {

    public int missingNumber(int[] nums) {
        //解法一 排序
        // Arrays.sort(nums);
        // for(int i = 0; i < nums.length; i++){
        //     if(nums[i] != i) return i;
        // }
        // return nums.length;

        //解法二 哈希表
        // Set<Integer> set = new HashSet<>();
        // for(int i = 0; i < nums.length; i++) set.add(nums[i]);
        // for(int i = 0; i <= nums.length; i++)
        //     if(!set.contains(i)) return i;
        // return -1;

        //解法三 位运算
        // int res = nums.length;
        // for(int i = 0; i < nums.length; i++){
        //     res ^= nums[i] ^ i;
        // }
        // return res;

        //解法四 数学
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //（1~n的和-现有元素和
        return nums.length * (nums.length + 1) / 2 - sum;
    }

    public static void main(String[] args) {

        int[] arrays = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        //3,0,1
        Solution solution = new Solution();
        int num = solution.missingNumber(arrays);
        //8
        System.out.println(num);


    }

}

