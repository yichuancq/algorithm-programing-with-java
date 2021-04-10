package com.example.algorithm.adjlistgraph;

import com.example.algorithm.matrixgraph.MatrixGraph;
import com.example.algorithm.triple.Triple;

public class FloydTest {
    public static void main(String args[]) {
        //【例7.1】  带权图的存储及操作。
        String[] vertices = {"A", "B", "C", "D", "E"};
        //带权无向图G3的顶点集合（除F） ，图7.13
        Triple[] edges = {new Triple(0, 1, 45),
                new Triple(0, 2, 28),
                new Triple(0, 3, 10),
                new Triple(1, 0, 45),
                new Triple(1, 2, 12),
                new Triple(1, 4, 21),
                new Triple(2, 0, 28),
                new Triple(2, 1, 12),
                new Triple(2, 3, 17),
                new Triple(2, 4, 26),
                new Triple(3, 0, 10),
                new Triple(3, 2, 17),
                new Triple(3, 4, 15),
                new Triple(4, 1, 21),
                new Triple(4, 2, 26),
                new Triple(4, 3, 15)};
        //G3的边集合（除F）
        MatrixGraph<String> graph = new MatrixGraph<String>(vertices, edges);
        //邻接矩阵表示的图
//        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);
// 邻接表表示的图
//        System.out.println("带权无向图G3（除顶点F），"+graph.toString());

        //（1）插入边（2）插入顶点
        System.out.println("插入顶点F，插入边(D,F,13)和(E,F,11)");//图7.14
        int i = graph.insertVertex("F");                     //插入顶点F，扩容
        graph.insertEdge(3, i, 13);                          //插入边(D,F,13)
        graph.insertEdge(new Triple(i, 3, 13));              //插入边(F,D,13)
        graph.insertEdge(4, i, 11);                          //插入边(E,F,11)
        graph.insertEdge(new Triple(i, 4, 11));              //插入边(F,E,11)
        System.out.println("带权无向图G3，" + graph.toString());
//        graph.insertEdge(5,6,0);                           //插入边，序号越界

        //（3）删除边（4）删除顶点
//        graph.removeEdge(5,6);                             //删除边，序号越界
/*        graph.removeEdge(4,i);                             //删除边(E,F,11)
        graph.removeEdge(new Triple(i,4,0));               //删除边(F,E,11)
        System.out.println("删除边(E,F,11)，图7.21（a）无向图G1，"+graph.toString());

        //以下语句正确，有F顶点时遍历
        graph.removeVertex(3);                             //删除顶点D，图7.15
        i=graph.insertVertex("G");                         //插入顶点G
        System.out.println("删除顶点D，插入顶点G，"+graph.toString());
*/

        //7.3   图的遍历
/*        System.out.println("深度优先遍历连通无向图G3：");  //图7.22（a），【思考题7-1，7-2】遍历序列【思考题7-3，习题解答】生成树
        for (i=0; i<graph.vertexCount(); i++)
            graph.DFSTraverse(i);

        System.out.println("广度优先遍历连通图无向图G3：");
        for (i=0; i<graph.vertexCount(); i++)
            graph.BFSTraverse(i);

        //@author：Yeheya。2014-8-4
*/
        //7.4.2   最小生成树的构造算法
        System.out.println("带权无向图G3，prim算法");
        graph.minSpanTree();                //prim

        //7.5.1   非负权值的单源最短路径（Dijkstra算法）
        System.out.println("带权无向图G3，Dijkstra算法");
        //图10.6
        for (i = 0; i < graph.vertexCount(); i++) {
            //顶点vi的单源最短路径，Dijkstra算法
            graph.shortestPath(i);
        }

        /*        //习题7
        System.out.print("有"+graph.edgeCount()+"条边，");
        i=0;
        System.out.println("顶点"+graph.get(i)+"的入度是"+graph.indegree(i)+
                                               "，出度是"+graph.outdegree(i));
*/
    }

}
