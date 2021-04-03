package com.example.algorithm.lottery;

/**
 * 杨辉三角
 */
public class YangHuiRetangle {

    static void func2() {
        int[][] arr = new int[10][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[i + 1];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j == 0 || i == j) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int num = (arr.length - i) / 2;
            for (int k = 0; k <= num; k++) {
                System.out.print("\t");
            }
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }

    }

    /**
     *
     */
    static void show() {
        System.out.println("打印图形");
        int rows = 10;

        for (int i = 0; i < rows; i++) {
            int number = 1;
            // 打印空格字符串
            System.out.format("%" + (rows - i) * 2 + "s", "");
            for (int j = 0; j <= i; j++) {
                System.out.format("%4d", number);
                number = number * (i - j) / (j + 1);
            }
            System.out.println();
        }

    }

    //    static void show() {
//        System.out.println("打印图形");
//        int n = 10;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j <= i; j++) {
//                //System.out.print(""+j);
//                // System.out.print("*");
//            }
//            System.out.println("");
//        }
//    }
    public static void main(String[] args) {
        YangHuiRetangle.show();
    }
}
