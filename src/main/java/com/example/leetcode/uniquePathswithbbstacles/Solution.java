package com.example.leetcode.uniquePathswithbbstacles;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 */
public class Solution {

    /**
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == 1 && j == 1) continue;
                //障碍物
                if (obstacleGrid[i - 1][j - 1] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        Solution solution = new Solution();
        int ways = solution.uniquePathsWithObstacles(obstacleGrid);
        /**
         *
         * 3x3 网格的正中间有一个障碍物。
         * 从左上角到右下角一共有 2 条不同的路径：
         * 1. 向右 -> 向右 -> 向下 -> 向下
         * 2. 向下 -> 向下 -> 向右 -> 向右
         */
        System.out.println(ways);
    }


}
