package com.example.learn.minispanningtree;

/***
 * 边节点
 */
public class Edge implements Comparable<Edge> {
    private Vertex start;
    private Vertex end;
    private int weight; // 权重

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}