package com.example.leetcode.countnegatives;

/**
 * 统计矩阵里面的负数个数
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列
 * <p>
 * 示例 1：
 * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * 输出：8
 * 解释：矩阵中共有 8 个负数。
 */
public class Solution {

    /**
     * @param grid
     * @return
     */
    public int countNegatives2(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            int key = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    key = grid[i][j];
                    break;
                }
            }
            if (key < 0) {
                //key为一维数组中的第一个负数，因为后面的负数比它小
                System.out.println("key:" + key);
                int index = binarySearch(grid[i], 0, grid[i].length, key);
                int temp = grid[i].length - index;
                count += temp;
                System.out.println("===");
            }
        }
        return count;
    }

    /**
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    private int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            System.out.println(String.format("left :%d, right: %d", a[low], a[high]));
            int mid = (low + high) >>> 1;
            int midVal = a[mid];
            if (midVal < key) {
                high = mid - 1;
            } else if (midVal > key) {
                low = mid + 1;
            } else if (mid > 0 && a[mid - 1] <= midVal) {
                high = mid - 1;
            } else {
                return mid;
            }// key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] grid = {{4, 3, 2, -1, -1, -2}, {3, 2, 1, -1}, {-1, -1, -1, -2}, {-1, -1, -2, -3, -3, -5, -6}};
//        int[][] grid = {{5, 1, 0}, {-5, -5, -5}};
        //int[][] grid = {{3, 1}, {1, 0}};
        // int[][] grid = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        Solution solution = new Solution();
        int n = solution.countNegatives(grid);
        System.out.println("负数个数：" + n);
        int n2 = solution.countNegatives2(grid);
        System.out.println("负数个数：" + n2);
    }
}
