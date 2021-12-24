package com.example.leetcode.parkingsystem;

import java.util.HashMap;

/**
 * 请你给一个停车场设计一个停车系统。停车场总共有三种不同大小的车位：大，中和小，每种尺寸分别有固定数目的车位
 * ParkingSystem(int big, int medium, int small)初始化ParkingSystem类，三个参数分别对应每种停车位的数目。
 * bool addCar(int carType)检查是否有carType对应的停车位。carType有三种类型：大，中，小，分别用数字1，2和3表示
 * 一辆车只能停在carType对应尺寸的停车位中。如果没有空车位，请返回false，否则将该车停入车位并返回true
 */
public class ParkingSystem {

    private String[] carTypes = {"big", "medium", "small"};
    private HashMap hashMap = new HashMap();

    public ParkingSystem(int big, int medium, int small) {
        hashMap.put(carTypes[0], big);
        hashMap.put(carTypes[1], medium);
        hashMap.put(carTypes[2], small);
    }

    /**
     * 有三种类型：大，中，小，分别用数字 1， 2 和 3 表示
     *
     * @param carType
     * @return
     */
    public boolean addCar(int carType) {
        final String typeKey = carTypes[carType - 1];
        int amount = Integer.valueOf(hashMap.get(typeKey).toString());
        if (amount <= 0) {
            return false;
        } else {
            hashMap.put(typeKey, --amount);
            return true;
        }
    }

    public static void main(String[] args) {
        //设计一个停车系统
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        boolean flag = parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
        System.out.println(flag);
        flag = parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
        System.out.println(flag);
        flag = parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
        System.out.println(flag);
        flag = parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
        System.out.println(flag);
    }


}
