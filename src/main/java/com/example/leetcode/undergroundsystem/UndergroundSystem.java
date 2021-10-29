package com.example.leetcode.undergroundsystem;

/**
 * 1.checkIn(int id, string stationName, int t)
 * <p>
 * 编号为id的乘客在 t时刻进入地铁站stationName。
 * 一个乘客在同一时间只能在一个地铁站进入或者离开。
 * 2.checkOut(int id, string stationName, int t)
 * <p>
 * 编号为id的乘客在 t时刻离开地铁站 stationName。
 * 3.getAverageTime(string startStation, string endStation)
 * <p>
 * 返回从地铁站startStation到地铁站endStation的平均花费时间。
 * 平均时间计算的行程包括当前为止所有从startStation直接到达endStation的行程。
 * <p>
 * 调用getAverageTime时，询问的路线至少包含一趟行程。
 * 你可以假设所有对checkIn和checkOut的调用都是符合逻辑的。也就是说，如果一个顾客在 t1时刻到达某个地铁站，那么他离开的时间t2一定满足t2 > t1。
 * 所有的事件都按时间顺序给出。
 */
class UndergroundSystem {
    /**
     *
     */
    public UndergroundSystem() {

    }

    /***
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkIn(int id, String stationName, int t) {

    }

    /***
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkOut(int id, String stationName, int t) {

    }

    /**
     * @param startStation
     * @param endStation
     * @return
     */
    public double getAverageTime(String startStation, String endStation) {
        return 0.0d;
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        // 返回 14.0。从 "Paradise"（时刻 8）到 "Cambridge"(时刻 22)的行程只有一趟
        undergroundSystem.getAverageTime("Paradise", "Cambridge");
        undergroundSystem.getAverageTime("Leyton", "Waterloo");
        // 返回 11.0。总共有 2 躺从 "Leyton" 到 "Waterloo" 的行程，编号为 id=45 的乘客出发于 time=3 到达于 time=15，编号为 id=27
        // 的乘客于 time=10 出发于 time=20 到达。所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0
        undergroundSystem.checkIn(10, "Leyton", 24);
        // 返回 11.0
        undergroundSystem.getAverageTime("Leyton", "Waterloo");
        undergroundSystem.checkOut(10, "Waterloo", 38);
        // 返回 12.0
        undergroundSystem.getAverageTime("Leyton", "Waterloo");

    }
}
