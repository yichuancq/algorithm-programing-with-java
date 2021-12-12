package com.example.leetcode.construct;


/**
 * 给你一个n*n矩阵 grid,矩阵由若干0和1组成。请你用四叉树表示该矩阵grid 。
 * 你需要返回能表示矩阵的四叉树的根结点。
 * 注意，当isLeaf为False 时，你可以把True或者False赋值给节点，两种值都会被判题机制接受 。
 */
public class Solution {
    public Node construct(int[][] grid) {
        return fun(grid, 0, grid[0].length, 0, grid.length);
    }

    public Node fun(int[][] grid, int left, int right, int top, int bottom) {
        Node root = null;
        int key = grid[top][left];
        for (int i = top; i < bottom; i++) {
            for (int j = left; j < right; j++) {
                if (grid[i][j] != key) {
                    Node topLeft = fun(grid, left, (left + right) / 2, top, (top + bottom) / 2);
                    Node topRight = fun(grid, (left + right) / 2, right, top, (top + bottom) / 2);
                    Node bottomLeft = fun(grid, left, (left + right) / 2, (top + bottom) / 2, bottom);
                    Node bottomRight = fun(grid, (left + right) / 2, right, (top + bottom) / 2, bottom);
                    root = new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
                    return root;
                }
            }
        }
        root = new Node();
        root.val = key == 1 ? true : false;
        root.isLeaf = true;
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 0, 0}};
        Node node = solution.construct(grid);
        System.out.println(node);
    }

}
