package com.example.learn.adjlistgraph;

import com.example.learn.triple.Triple;
import org.junit.Test;

public class AdjListGraphTest {


    /**
     * 带权有向图
     */
    @Test
    public void test1() {
        String[] vertices = {"A", "B", "C", "D", "E"};
        Triple edges[] = {
                new Triple(0, 1, 10),
                new Triple(0, 3, 30),
                new Triple(0, 4, 99),
                new Triple(1, 2, 50),
                new Triple(1, 3, 40),
                new Triple(2, 4, 10),
                new Triple(3, 2, 20),
                new Triple(3, 4, 60)};
//      MatrixGraph<String> graph = new MatrixGraph<String>(vertices, edges);    //图7.12
        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);//图7.17
        System.out.print("带权有向图G4，" + graph.toString());


        //7.3   图的遍历
        System.out.println("深度优先遍历有向图G4：");                //图7.22（b），【思考题7-1~2，习题解答】遍历序列
        for (int i = 0; i < graph.vertexCount(); i++)
            graph.DFSTraverse(i);

        System.out.println("广度优先遍历有向图G4：");
        for (int i = 0; i < graph.vertexCount(); i++)
            graph.BFSTraverse(i);
        //7.5.1   非负权值的单源最短路径（Dijkstra算法）
        System.out.println("带权有向图G4，Dijkstra算法，单源最短路径如下：");
        for (int i = 0; i < graph.vertexCount(); i++)
            //顶点vi的单源最短路径，Dijkstra算法
            graph.shortestPath(i);
        //调用Floyd算法求带权图每对顶点间的最短路径
        graph.shortestPath();

    }
}
