package com.example.designpattern.observerbellevent;

//具体观察者类：老师事件监听器
class TeachEventListener implements BellEventListener {
    public void heardBell(RingEvent e) {
        if (e.getSound()) {
            System.out.println("老师上课了...");
        } else {
            System.out.println("老师下课了...");
        }
    }
}
