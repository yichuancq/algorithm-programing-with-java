package com.example.algorithm.rob;


import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，
 * 计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12
 */
public class Solution {
    /**
     * memo[i] 表示考虑抢劫 nums[i...n] 所能获得的最大收益
     */
    private int[] ints;

    /**
     * 方式一：记忆化搜索
     * ① 状态：考虑抢劫 nums[index...num.length） 这个范围内的所有房子
     * ② 状态转移：tryRob(n) = Max{rob(0) + tryRob(2), rob(1) + tryRob(3)... rob(n-3) + tryRob(n-1), rob(n-2), rob(n-1)}
     */
    public int rob1(int[] nums) {
        ints = new int[nums.length];
        Arrays.fill(ints, -1);
        return tryRob(nums, 0);
    }


    /**
     * @param nums
     * @param index
     * @return
     */
    private int tryRob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        // 记忆化搜索可以避免重叠子问题的重复运算
        if (ints[index] != -1) {
            return ints[index];
        }
        // 下面是对状态转移方程的描述
        int res = 0;
        for (int i = index; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        ints[index] = res;
        return res;
    }

    /**
     * 方式二：动态规划
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // memo[i] 表示考虑抢劫 nums[i...n-1] 所能获得最大收益（不是说一定从 i 开始抢劫）
        int[] memo = new int[n];
        // 先考虑最简单的情况
        memo[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // memo[i] 的取值在考虑抢劫 i 号房子和不考虑抢劫之间取最大值
            memo[i] = Math.max(nums[i] + (i + 2 >= n ? 0 : memo[i + 2]), nums[i + 1] + (i + 3 >= n ? 0 : memo[i + 3]));
        }
        return memo[0];
    }

    /**
     * 动态规划简化版
     */
    public int rob(int[] nums) {
        ints = new int[nums.length];
        Arrays.fill(ints, -1);

        int n = nums.length;
        if (n <= 1) return n == 0 ? 0 : nums[0];
        ints[0] = nums[0];
        //元素为2取最大值
        ints[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            ints[i] = Math.max(ints[i - 1], nums[i] + ints[i - 2]);
        }
        return ints[n - 1];
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3, 1, 21, 17, 19, 13, 11};
        Solution solution = new Solution();
        int n = solution.rob(num);
        System.out.println(n);

    }
}
