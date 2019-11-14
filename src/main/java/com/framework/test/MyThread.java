package com.framework.test;

import java.util.Random;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2017年9月7日 上午8:54:00<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public class MyThread extends Thread{

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
        Thread thread2 = new MyThread();
        thread2.start();
    }

    public void run() {
        // TODO Auto-generated method stub
        super.run();
        System.out.println(new Random().nextInt(4));
    }
}
