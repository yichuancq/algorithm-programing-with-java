package com.example.leetcode.purchaseplans;

import java.util.Arrays;

/**
 * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，
 * 请问他有多少种采购方案。
 * <p>
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 * <p>
 * 示例：
 * 输入：nums = [2,2,1,9], target = 10
 * 输出：4
 * 解释：符合预算的采购方案如下：
 * nums[0] + nums[1] = 4
 * nums[0] + nums[2] = 3
 * nums[1] + nums[2] = 3
 * nums[2] + nums[3] = 10
 */
public class PurchasePlans {

    /**
     * 方法1
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        int result = 0;
        int targetTemp = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length && targetTemp <= target; i++) {
            for (int j = 0; j < i; j++) {
                if (i != j) {
                    targetTemp = nums[j] + nums[i];
                    if (targetTemp <= target) {
                        result += 1;
                        //System.out.println(String.format("nums[%d]+nums[%d] =%d", j, i, targetTemp));
                    }
                }

            }
        }
        return result;
    }

    /**
     * 方法2
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans2(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (target > nums[0]) return 1;
            else return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] <= target) {
                res %= 1000000007;
                res += j - i;
                i++;
            } else
                j--;
        }
        return res % 1000000007;
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans3(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        long total = 0;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                total += (right - left);
                left++;
            }
        }
        return (int) (total % 1000000007);
    }

    public static void main(String[] args) {
        PurchasePlans purchasePlans = new PurchasePlans();
        int[] nums = {2, 2, 1, 9};
        //
        int target = 10;
        int n = purchasePlans.purchasePlans3(nums, target);
        System.out.println(n);
    }

}
