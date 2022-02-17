package com.example.examination.light;

/**
 * 车辆线程
 */
public class Car extends Thread {
    String name = "";
    /***灯作为私有变量，车辆根据灯的状态决定是否要停止***/
    private Lighter lighter;

    public Car(String name, Lighter l) {
        this.name = name;
        this.lighter = l;
    }

    @Override
    public void run() {
        if ("red".equals(lighter.state)) {
            System.out.println(this.name + ":等待中");
        } else {
            System.out.println(this.name + ":通过了红绿灯");
        }
    }
}