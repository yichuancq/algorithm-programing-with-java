package com.example.examination.light;

/**
 * @author yichuan
 */
public class RelightTest {
    public static void main(String[] args) throws InterruptedException {
        Lighter l=new Lighter();
        //红绿灯开始运行
        l.start();
        //生成20个车辆，依次通过红绿灯
        for(int i=0;i<20;i++){
            Car c=new Car("car"+i+1,l);
            //当前车辆睡眠1s
            Thread.sleep(1000);
            c.start();
        }
    }
}