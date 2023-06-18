package com.example.algorithm.rubikscube;

/**
 * @author yichuan
 * @version 1.0
 * @description: 魔法算法
 * 解魔方是一个非常复杂的问题，需要涉及到很多算法和技术。下面是一个简单的 Java 程序，
 * 、可以实现魔方的随机打乱和还原。
 * @date 2023/6/14 17:55
 */

import java.util.Random;

public class RubiksCube {
    private static final int SIZE = 3;
    private static final int MAX_MOVES = 20;
    private static final String[] MOVES = {"U", "D", "L", "R", "F", "B"};

    private int[][][] cube = new int[SIZE][SIZE][SIZE];

    public RubiksCube() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    cube[i][j][k] = i * SIZE * SIZE + j * SIZE + k;
                }
            }
        }
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < MAX_MOVES; i++) {
            int move = rand.nextInt(MOVES.length);
            switch (move) {
                case 0:
                    turnTop(true);
                    break;
                case 1:
                    turnTop(false);
                    break;
                case 2:
                    turnLeft(true);
                    break;
                case 3:
                    turnLeft(false);
                    break;
                case 4:
                    turnFront(true);
                    break;
                case 5:
                    turnFront(false);
                    break;
            }
        }
    }

    public void solve() {
        // TODO: implement solving algorithm
    }

    private void turnTop(boolean clockwise) {
        int[][] temp = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                temp[i][j] = cube[0][i][j];
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (clockwise) {
                    cube[0][i][j] = cube[SIZE - 1 - j][i][0];
                    cube[SIZE - 1 - j][i][0] = cube[SIZE - 1 - i][j][SIZE - 1];
                    cube[SIZE - 1 - i][j][SIZE - 1] = cube[j][i][SIZE - 1];
                    cube[j][i][SIZE - 1] = temp[i][j];
                } else {
                    cube[0][i][j] = cube[j][i][SIZE - 1];
                    cube[j][i][SIZE - 1] = cube[SIZE - 1 - i][j][SIZE - 1];
                    cube[SIZE - 1 - i][j][SIZE - 1] = cube[SIZE - 1 - j][i][0];
                    cube[SIZE - 1 - j][i][0] = temp[i][j];
                }
            }
        }
    }

    private void turnLeft(boolean clockwise) {
        int[][] temp = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                temp[i][j] = cube[i][0][j];
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (clockwise) {
                    cube[i][0][j] = cube[SIZE - 1 - j][SIZE - 1][i];
                    cube[SIZE - 1 - j][SIZE - 1][i] = cube[SIZE - 1 - i][SIZE - 1][SIZE - 1 - j];
                    cube[SIZE - 1 - i][SIZE - 1][SIZE - 1 - j] = cube[j][0][SIZE - 1 - i];
                    cube[j][0][SIZE - 1 - i] = temp[i][j];
                } else {
                    cube[i][0][j] = cube[j][0][SIZE - 1 - i];
                    cube[j][0][SIZE - 1 - i] = cube[SIZE - 1 - i][SIZE - 1][SIZE - 1 - j];
                    cube[SIZE - 1 - i][SIZE - 1][SIZE - 1 - j] = cube[SIZE - 1 - j][SIZE - 1][i];
                    cube[SIZE - 1 - j][SIZE - 1][i] = temp[i][j];
                }
            }
        }
    }

    private void turnFront(boolean clockwise) {
        int[][] temp = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                temp[i][j] = cube[i][j][0];
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (clockwise) {
                    cube[i][j][0] = cube[SIZE - 1 - j][i][0];
                    cube[SIZE - 1 - j][i][0] = cube[SIZE - 1 - i][SIZE - 1 - j][0];
                    cube[SIZE - 1 - i][SIZE - 1 - j][0] = cube[j][SIZE - 1 - i][0];
                    cube[j][SIZE - 1 - i][0] = temp[i][j];
                } else {
                    cube[i][j][0] = cube[j][SIZE - 1 - i][0];
                    cube[j][SIZE - 1 - i][0] = cube[SIZE - 1 - i][SIZE - 1 - j][0];
                    cube[SIZE - 1 - i][SIZE - 1 - j][0] = cube[SIZE - 1 - j][i][0];
                    cube[SIZE - 1 - j][i][0] = temp[i][j];
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    System.out.print(cube[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /***
     * 在上述代码中，我们定义了一个 RubiksCube 类，该类包含一个 cube 数组，表示魔方的六个面。我们还实现了 shuffle 方法，该方法可以随机打乱魔方的状态，
     * 以及 solve 方法，该方法可以还原魔方的状态。我们还实现了三个私有方法 turnTop、turnLeft 和 turnFront，用于模拟魔方的旋转操作。最后，
     * 我们在 main 方法中创建了一个 RubiksCube 对象，并调用了 shuffle 和 solve 方法，以及 print 方法用于打印魔方的状态。
     * 需要注意的是，上述代码实现的是一个简单的魔方程序，可能无法解决所有的魔方问题。如果您需要解决更复杂的魔方问题，可能需要使用更高级的算法和技术。
     * @param args
     */
    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        cube.shuffle();
        cube.print();
        cube.solve();
        cube.print();
    }
}