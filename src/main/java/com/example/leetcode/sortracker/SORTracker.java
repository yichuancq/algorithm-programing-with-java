package com.example.leetcode.sortracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一个观光景点由它的名字name 和景点评分score组成，其中name是所有观光景点中唯一的字符串，score是一个整数。景点按照最好到最坏排序。
 * 景点评分 越高，这个景点越好。如果有两个景点的评分一样，那么 字典序较小的景点更好。
 * 你需要搭建一个系统，查询景点的排名。初始时系统里没有任何景点。
 * 这个系统支持：添加景点，每次添加一个景点。
 * 查询已经添加景点中第i好的景点，其中i是系统目前位置查询的次数（包括当前这一次）。比方说，如果系统正在进行第4次查询，
 * 那么需要返回所有已经添加景点中第4好的。注意，测试数据保证任意查询时刻，查询次数都 不超过系统中景点的数目。
 */
public class SORTracker {
    private List<Tracker> trackerList = new ArrayList<>();
    private Integer getIndex = 0;

    public SORTracker() {
    }

    /**
     * 添加景点
     *
     * @param name
     * @param score
     */
    public void add(String name, int score) {
        Tracker tracker = new Tracker(name, score);
        if (!trackerList.contains(tracker)) {
            System.out.println("添加 name:" + name + "\tscore:" + score);
            trackerList.add(tracker);
        }
    }

    /**
     * 获取景点
     *
     * @return
     */
    public String get() {
        //按score排序(Integer类型)
        List<Tracker> trackerListOrder = trackerList.stream().sorted(Comparator.comparing(Tracker::getScore).reversed()
                //
                .thenComparing(Tracker::getName)).collect(Collectors.toList());
        System.out.println(trackerListOrder);
        Tracker tracker = trackerListOrder.get(getIndex);
        getIndex++;
        System.out.println("index:" + getIndex);
        System.out.println("get name:" + tracker.getName());
        System.out.println("=====");
        return tracker.getName();
    }

    public static void main(String[] args) {
        // 序列顺序查询
        SORTracker tracker = new SORTracker(); // 初始化系统
        tracker.add("bradford", 2); // 添加 name="bradford" 且 score=2 的景点。
        tracker.add("branford", 3); // 添加 name="branford" 且 score=3 的景点。
        //[Tracker{name='branford', score=3}, Tracker{name='bradford', score=2}]
        //返回 name='branford', score=3
        tracker.get();              // 从好带坏的景点为：branford ，bradford 。
        // 注意到 branford 比 bradford 好，因为它的 评分更高 (3 > 2) 。
        // 这是第 1 次调用 get() ，所以返回最好的景点："branford" 。
        tracker.add("alps", 2);     // 添加 name="alps" 且 score=2 的景点。
        //[Tracker{name='branford', score=3}, Tracker{name='alps', score=2}, Tracker{name='bradford', score=2}]
        //返回 name='alps', score=2
        tracker.get();              // 从好到坏的景点为：branford, alps, bradford 。
        // 注意 alps 比 bradford 好，虽然它们评分相同，都为 2 。
        // 这是因为 "alps" 字典序比 "bradford" 小。
        // 返回第 2 好的地点 "alps" ，因为当前为第 2 次调用 get() 。
        tracker.add("orland", 2);   // 添加 name="orland" 且 score=2 的景点。
        //[Tracker{name='branford', score=3}, Tracker{name='alps', score=2},
        // Tracker{name='bradford', score=2}, Tracker{name='orland', score=2}]
        //返回  get name:bradford
        tracker.get();              // 从好到坏的景点为：branford, alps, bradford, orland 。
        // 返回 "bradford" ，因为当前为第 3 次调用 get() 。
        tracker.add("orlando", 3);  // 添加 name="orlando" 且 score=3 的景点。
        //[Tracker{name='branford', score=3}, Tracker{name='orlando', score=3}, Tracker{name='alps', score=2},
        // Tracker{name='bradford', score=2}, Tracker{name='orland', score=2}]
        tracker.get(); // 从好到坏的景点为：branford, orlando, alps, bradford, orland 。
        // 返回 "bradford".
        tracker.add("alpine", 2);   // 添加 name="alpine" 且 score=2 的景点。
        tracker.get();// 从好到坏的景点为：branford, orlando, alpine, alps, bradford, orland 。
        // 返回 "bradford" 。
        tracker.get(); // 从好到坏的景点为：branford, orlando, alpine, alps, bradford, orland 。
        // 返回 "orland" 。
    }
}
