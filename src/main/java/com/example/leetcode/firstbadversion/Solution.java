package com.example.leetcode.firstbadversion;

/**
 * 一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，
 * 所以错误的版本之后的所有版本都是错的。假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 示例 1：
 * 输入：n = 5, bad = 4
 * 输出：4
 * 解释：
 * 调用 isBadVersion(5)-> true
 * 调用 isBadVersion(4)-> true
 * 调用 isBadVersion(3) -> false
 * 所以，4 是第一个错误的版本。
 * <p>
 * 示例 2：
 * 输入：n = 1, bad = 1
 * 输出：1
 */
public class Solution {

    /**
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            System.out.println(String.format("left:%d right:%d", left, right));
            int middle = left + ((right - left) / 2);
            System.out.println("mid:" + middle);
            //如果中间值为bad 继续向前查找
            boolean midFlag = isBadVersion(middle);
            System.out.println("是否为错误版本: " + midFlag);
            if (midFlag) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * 假定内置函数
     *
     * @param version
     * @return
     */
    private boolean isBadVersion(int version) {
        if (version >= 4) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        //  int n = 5;
        int result = solution.firstBadVersion(n);
        System.out.println(result);

    }

}
