package com.example.algorithm.bellmanford;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 贝尔曼-福特算法(Bellman–Ford algorithm )用于计算出起点到各个节点的最短距离，支持存在负权重的情况
 * </>
 * 初始化时将起点s到各个顶点v的距离dist(s->v)赋值为∞，dist(s->s)赋值为0
 * 后续进行最多n-1次遍历操作(n为顶点个数,上标的v输入法打不出来...),对所有的边进行松弛操作,假设:
 * 所谓的松弛，以边ab为例，若dist(a)代表起点s到达a点所需要花费的总数， dist(b)代表起点s到达b点所需要花费的总数,
 * weight(ab)代表边ab的权重， 若存在:(dist(a) +weight(ab)) < dist(b)
 * 则说明存在到b的更短的路径,s->...->a->b,更新b点的总花费为(dist(a) +weight(ab))，父节点为a
 * 遍历都结束后，若再进行一次遍历，还能得到s到某些节点更短的路径的话，则说明存在负环路
 * 思路上与狄克斯特拉算法(Dijkstra algorithm)最大的不同是每次都是从源点s重新出发进行"松弛"更新操作，
 * 而Dijkstra则是从源点出发向外扩逐个处理相邻的节点，不会去重复处理节点，这边也可以看出Dijkstra效率相对更高点。
 */
public class BellmanFord {
    public static void main(String[] args) {

        //创建图
        Edge ab = new Edge("A", "B", -1);
        Edge ac = new Edge("A", "C", 4);
        Edge bc = new Edge("B", "C", 3);
        Edge be = new Edge("B", "E", 2);
        Edge ed = new Edge("E", "D", -3);
        Edge dc = new Edge("D", "C", 5);
        Edge bd = new Edge("B", "D", 2);
        Edge db = new Edge("D", "B", 1);

        //需要按图中的步骤步数顺序建立数组，否则就是另外一幅图了，
        //从起点A出发，步骤少的排前面
        Edge[] edges = new Edge[]{ab, ac, bc, be, bd, ed, dc, db};

        //存放到各个节点所需要消耗的时间
        HashMap<String, Integer> costMap = new HashMap<String, Integer>();
        //到各个节点对应的父节点
        HashMap<String, String> parentMap = new HashMap<String, String>();


        //初始化各个节点所消费的，当然也可以再遍历的时候判断下是否为Null
        //i=0的时候
        costMap.put("A", 0); //源点
        costMap.put("B", Integer.MAX_VALUE);
        costMap.put("C", Integer.MAX_VALUE);
        costMap.put("D", Integer.MAX_VALUE);
        costMap.put("E", Integer.MAX_VALUE);

        //进行节点数n-1次循环
        for (int i = 1; i < costMap.size(); i++) {
            boolean hasChange = false;
            for (int j = 0; j < edges.length; j++) {
                Edge edge = edges[j];
                //该边起点目前总的路径大小
                int startPointCost = costMap.get(edge.getStartPoint()) == null ? 0 : costMap.get(edge.getStartPoint());
                //该边终点目前总的路径大小
                int endPointCost = costMap.get(edge.getEndPoint()) == null ? Integer.MAX_VALUE : costMap.get(edge.getEndPoint());
                //如果该边终点目前的路径大小 > 该边起点的路径大小 + 该边权重 ，说明有更短的路径了
                if (endPointCost > (startPointCost + edge.getWeight())) {
                    costMap.put(edge.getEndPoint(), startPointCost + edge.getWeight());
                    parentMap.put(edge.getEndPoint(), edge.getStartPoint());
                    hasChange = true;
                }
            }
            if (!hasChange) {
                //经常还没达到最大遍历次数便已经求出解了，此时可以优化为提前退出循环
                break;
            }
        }

        //在进行一次判断是否存在负环路
        boolean hasRing = false;
        for (int j = 0; j < edges.length; j++) {
            Edge edge = edges[j];
            int startPointCost = costMap.get(edge.getStartPoint()) == null ? 0 : costMap.get(edge.getStartPoint());
            int endPointCost = costMap.get(edge.getEndPoint()) == null ? Integer.MAX_VALUE : costMap.get(edge.getEndPoint());
            if (endPointCost > (startPointCost + edge.getWeight())) {
                System.out.print("\n图中存在负环路，无法求解\n");
                hasRing = true;
                break;
            }
        }

        if (!hasRing) {
            //打印出到各个节点的最短路径
            for (String key : costMap.keySet()) {
                System.out.print("\n到目标节点" + key + "最低耗费:" + costMap.get(key));
                if (parentMap.containsKey(key)) {
                    List<String> pathList = new ArrayList<String>();
                    String parentKey = parentMap.get(key);
                    while (parentKey != null) {
                        pathList.add(0, parentKey);
                        parentKey = parentMap.get(parentKey);
                    }
                    pathList.add(key);
                    String path = "";
                    for (String k : pathList) {
                        path = path.equals("") ? path : path + " --> ";
                        path = path + k;
                    }
                    System.out.print("，路线为" + path);
                }
            }
        }


    }


    /**
     * 代表"一条边"的信息对象
     */
    static class Edge {
        //起点id
        private String startPoint;
        //结束点id
        private String endPoint;
        //该边的权重
        private int weight;

        public Edge(String startPoint, String endPoint, int weight) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.weight = weight;
        }

        public String getStartPoint() {
            return startPoint;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public int getWeight() {
            return weight;
        }
    }

}
