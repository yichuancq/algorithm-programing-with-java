package com.example.algorithm.tsp;

/**
 * 旅行商问题
 *
 * @author yichuan
 */
public class TravelingSalesman {
    /***定义全局变量，用于计算当前已行走方案次数，初始化为0**/
    public int count = 0;
    /***定义完成一个行走方案的最短距离，初始化为100（PS：100此处表示比实际要大很多的距离）*/
    public int MinDistance = 100;
    /**
     * 使用二维数组的那个音图的路径相关距离长度
     **/
    public int[][] distance = {{0, 2, 5, 7}, {2, 0, 8, 3}, {5, 8, 0, 1}, {7, 3, 1, 0}};

    /***
     * start为开始进行排序的位置
     * step为当前正在行走的位置
     * n为需要排序的总位置数
     * Max为n!值
     */
    public void Arrange(int[] A, int start, int step, int n, int Max) {
        if (step == n) {
            // 当正在行走的位置等于城市总个数时
            ++count;
            //每完成一次行走方案，count自增1
            printArray(A);
            //输出行走路线方案及其总距离
        }
        if (count == Max)
            System.out.println("已完成全部行走方案！！！,最短路径距离为:" + MinDistance);
        else {
            for (int i = start; i < n; i++) {
                /** 第i个数分别与它后面的数字交换就能得到新的排列,从而能够得到n!次不同排序方案
                 * （PS：此处代码中递归的执行顺序有点抽象，具体解释详见本人另一篇博客：）
                 *算法笔记_017:递归执行顺序的探讨（Java）
                 */
                swapArray(A, start, i);
                Arrange(A, start + 1, step + 1, n, Max);
                swapArray(A, i, start);
            }
        }
    }

    /**
     * 交换数组中两个位置上的数值
     */

    public void swapArray(int[] A, int p, int q) {
        int temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    }

    /**
     * 输出数组A的序列，并输出当前行走序列所花距离，并得到已完成的行走方案中最短距离
     */
    public void printArray(int[] A) {
        for (int i = 0; i < A.length; i++)
            //输出当前行走方案的序列
            System.out.print(A[i] + "  ");

        int tempDistance = distance[A[0]][A[3]];
        //此处是因为，最终要返回出发地城市，所以总距离要加上最后到达的城市到出发点城市的距离
        for (int i = 0; i < (A.length - 1); i++) {
            //输出当前行走方案所花距离
            tempDistance += distance[A[i]][A[i + 1]];
        }

        if (MinDistance > tempDistance) {
            //返回当前已完成方案的最短行走距离
            MinDistance = tempDistance;
        }
        System.out.println("  行走路程总和：" + tempDistance);
    }

    public static void main(String[] args) {
        int[] A = {0, 1, 2, 3};
        TravelingSalesman test = new TravelingSalesman();
        test.Arrange(A, 0, 0, 4, 24);
        //此处Max = 4!=24
    }

}
