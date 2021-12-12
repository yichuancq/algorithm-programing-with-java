package com.example.leetcode.construct;

public class Solution2 {
    /**
     * 思路及算法
     * 考虑递归
     * 引入辅助函数 constructTree，逻辑为：
     * 1.该函数附带小分块的 左上角、右下角坐标，顺序依次排列为 上，左，下，右
     * 2.如果当前分块内的值都一样，说明是叶子节点，返回；否则，再继续切分为4个分块，找到中点，向四个小块递归
     * 3.到最后只有一个元素时递归终止
     *
     * @param grid
     * @return
     */
    public Node construct(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        return constructTree(grid, 0, 0, m - 1, n - 1);
    }

    //upper x/y && down x/y, 上，左，下，右
    public Node constructTree(int[][] grid, int ux, int uy, int dx, int dy) {
        boolean isLeaf = true;
        for (int i = ux; i <= dx; i++) {
            for (int j = uy; j <= dy; j++) {
                if (grid[i][j] != grid[ux][uy]) {
                    isLeaf = false;
                    break;
                }
            }
        }
        if (isLeaf) return new Node(grid[ux][uy] == 1, isLeaf);
        //middle x/y
        int mx = ux + dx >> 1, my = uy + dy >> 1;
        Node upLeft = constructTree(grid, ux, uy, mx, my);
        Node upRight = constructTree(grid, ux, my + 1, mx, dy);
        Node downLeft = constructTree(grid, mx + 1, uy, dx, my);
        Node downRight = constructTree(grid, mx + 1, my + 1, dx, dy);
        return new Node(true, isLeaf, upLeft, upRight, downLeft, downRight);
    }

}
