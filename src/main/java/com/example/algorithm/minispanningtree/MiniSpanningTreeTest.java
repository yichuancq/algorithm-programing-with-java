package com.example.algorithm.minispanningtree;

import org.junit.Test;

import java.util.List;

/**
 * 最小生成树
 */
public class MiniSpanningTreeTest {

    /**
     * (g, h)
     * (c, i)
     * (f, g)
     * (a, b)
     * (c, f)
     * (c, d)
     * (a, h)
     * (d, e)
     */
    @Test
    public void test() {
        MiniSpanningTree miniSpanningTree = new MiniSpanningTree();
        List<Edge> edges = miniSpanningTree.getTestData(); // 获取测试数据
        List<Edge> result = miniSpanningTree.miniSpanningTree(edges); // 得到最小生成树
        miniSpanningTree.printEdges(result); // 打印最小生成树的边

    }

}
