package com.example.leetcode.searchmatrix;

/**
 * 编写一个高效的算法来搜索mxn矩阵matrix中的一个目标值target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class Solution {

    /**
     * 二分搜索
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (matrix[i][mid] <= target) l = mid;
                else r = mid - 1;
            }
            if (matrix[i][r] == target) return true;
        }
        return false;
    }

    /**
     * 抽象BST
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (matrix[r][c] < target) r++;
            else if (matrix[r][c] > target) c--;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;
        boolean flag = solution.searchMatrix(matrix, target);
        System.out.println(flag);
    }

}
