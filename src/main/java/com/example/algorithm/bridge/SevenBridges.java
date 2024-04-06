package com.example.algorithm.bridge;

import java.util.Arrays;
import java.util.List;

/**
 * @author yichuan
 * @version 1.0
 * @description: SevenBridges
 * @date 2023/6/24 23:18
 */
public class SevenBridges {

    public static void main(String[] args) {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");

        Edge ab = new Edge(a, b);
        Edge ac = new Edge(a, c);
        Edge ad = new Edge(a, d);
        Edge be = new Edge(b, e);
        Edge bf = new Edge(b, f);
        Edge cg = new Edge(c, g);
        Edge dg = new Edge(d, g);
        Edge ef = new Edge(e, f);
        //
        a.edges = Arrays.asList(ab, ac, ad);
        b.edges = Arrays.asList(ab, be, bf);
        c.edges = Arrays.asList(ac, cg);
        d.edges = Arrays.asList(ad, dg);
        e.edges = Arrays.asList(be, ef);
        f.edges = Arrays.asList(bf, ef);
        g.edges = Arrays.asList(cg, dg);
        List<Node> nodes = Arrays.asList(a, b, c, d, e, f, g);
        int oddDegreeCount = 0;
        for (Node node : nodes) {
            int degree = node.edges.size();
            if (degree % 2 != 0) {
                oddDegreeCount++;
            }
        }
        if (oddDegreeCount > 2) {
            System.out.println("不存在欧拉回路");
        } else {
            System.out.println("存在欧拉回路");
        }
    }
}
