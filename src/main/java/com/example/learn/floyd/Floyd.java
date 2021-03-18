package com.example.learn.floyd;

/**
 * 给定一个加权连通图(无向的或有向的)，要求找出从每个定点到其他所有定点之间的最短路径以及最短路径的长度
 * tips:动态规划实现最短路径问题
 */
public class Floyd {
    /**
     * 根据有向图的权重矩阵及起始的中间节点路径矩阵，返回最终图的距离矩阵及中间节点路径矩阵
     */
    public static void getShortestPath(int[][] chart, int[][] path) {
        int len = chart.length;
        for (int k = 0; k < len; k++) {  //k = 0表示，增加中间顶点a，k自增，表示后继增加第k个中间顶点(依次为b,c，d...)
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    //新增一个中间顶点时，用该变量存储从i到k再到j的路径长度
                    int temp = 0;
                    //当A[i][k]和A[k][j]路径都可以行走,100表示两顶点不相通
                    if (chart[i][k] != 100 && chart[k][j] != 100)
                        temp = chart[i][k] + chart[k][j];
                    //如果当前i到j路径长度（包括无法达到情况）大于以k为中间顶点的路径时
                    if (chart[i][j] > temp && temp != 0) {
                        chart[i][j] = temp;
                        //当两顶点相通，且是最短路径时，把k+1存入中间节点路径矩阵path中(PS:0表示i到j之间没有中间节点，
                        // 1表示中间节点为a，所以此处为k+1，而不是用k，这样排除0的情况)
                        path[i][j] = k + 1;
                    }
                }
            }
        }
    }

    /**
     * 根据有向图的中间节点路径矩阵，以及两个顶点，返回这两个顶点之间的最短路径
     *
     * @param path
     * @param start
     * @param end
     * @return
     */
    public static String getOneShortestPath(int[][] path, int start, int end) {
        char startNode = (char) ('a' + start);
        char endNode = (char) ('a' + end);
        String nodePath = "";
        if (path[start][end] == 0) {
            nodePath += startNode + "->" + endNode;
            return nodePath;
        }
        int middle = path[start][end] - 1;
        //使用递归求解最短路径
        nodePath += getOneShortestPath(path, start, middle) + " , " + getOneShortestPath(path, middle, end);
        return nodePath;
    }

    /**
     * 输出有向图所有顶点之间的最短路径及最短路径长度
     *
     * @param path
     * @param result
     */
    public static void printShortestPath(int[][] path, int[][] result) {
        int len = path.length;
        for (int i = 0; i < len; i++) {
            char startNode = (char) ('a' + i);
            for (int j = 0; j < len; j++) {
                char endNode = (char) ('a' + j);
                String ijPath = startNode + "——>" + endNode + "最短路径为：";
                String nodePath = getOneShortestPath(path, i, j);
                System.out.println(ijPath + nodePath + " 。 路径长度为：" + result[i][j]);
            }
        }
    }

    public static void main(String args[]) {
        /*chart数组中,数组0，1，2，3等表示两顶点之间的权重值,即两顶点间的距离大小, 100表示两顶点不相通*/
        int[][] chart = {{0, 100, 3, 100}, {2, 0, 100, 100}, {100, 7, 0, 1}, {6, 100, 100, 0}};

        System.out.println("有向图chart的权重矩阵为(PS:其中值为100表示无穷大，即无法相通的路径)：");
        System.out.println("\t" + "a" + "\t" + "b" + "\t" + "c" + "\t" + "d");
        for (int i = 0; i < 4; i++) {
            char startNode = (char) ('a' + i);
            System.out.print(startNode + "\t");
            for (int j = 0; j < 4; j++)
                System.out.print(chart[i][j] + "\t");
            System.out.println();
        }
        /*path数组中，0表示两顶点相通,1表示两顶点间有一个中间节点a,2表示 两顶点间有一个中间节点b,3两顶点间有一个中间节点c,4两顶点间有一个中间节点d.
         * 100表示两顶点不相通*/
        int[][] path = {{0, 100, 0, 100}, {0, 0, 100, 100}, {100, 0, 0, 0}, {0, 100, 100, 0}};
        getShortestPath(chart, path);

        System.out.println("有向图chart的距离矩阵为：");
        System.out.println("\t" + "a" + "\t" + "b" + "\t" + "c" + "\t" + "d");
        for (int i = 0; i < 4; i++) {
            char startNode = (char) ('a' + i);
            System.out.print(startNode + "\t");
            for (int j = 0; j < 4; j++)
                System.out.print(chart[i][j] + "\t");
            System.out.println();
        }

        System.out.println("有向图chart的中间节点路径矩阵为(PS:值为0表示两节点直接相通,值为1表示两节点有一个中间节点a,值为2表示中间节点为b,依次类推)：");
        System.out.println("\t" + "a" + "\t" + "b" + "\t" + "c" + "\t" + "d");
        for (int i = 0; i < 4; i++) {
            char startNode = (char) ('a' + i);
            System.out.print(startNode + "\t");
            for (int j = 0; j < 4; j++)
                System.out.print(path[i][j] + "\t");
            System.out.println();
        }

        System.out.println("最终求取结果为：");
        printShortestPath(path, chart);


    }

}
