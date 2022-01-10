package com.example.examination.singleton;

/**
 * synchronized
 * 通过JDK自带的命令查看类的相关字节码信息：首先切换到类的对应目录执行命令生成编译后的.class 文件,然后执行
 * javap SynchronizedDemo
 * javac SynchronizedDemo.java
 * javap -c -s -v -l SynchronizedDemo.class
 * <p>
 * <p>
 * synchronized 同步语句块的实现使用的是 monitorenter 和 monitorexit指令，
 * 其中 monitorenter指令指向同步代码块的开始位置，monitorexit 指令则指明同步代码块的结束位置
 * 当执行指令时，线程试图获取锁也就是获取对象监视器monitor的持有权
 *
 * @author yichuan
 */
public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.method();
    }
}
