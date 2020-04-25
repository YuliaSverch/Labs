package com.java.ffeks.multithreading.countdown;

import com.java.ffeks.base.Lab3;
import com.java.ffeks.base.Lab4;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread app1 = new Thread(new CDLab3("App-1", countDownLatch));
        Thread app2 = new Thread(new CDLab4("App-2", countDownLatch));

        // initialize applications
        app1.start();
        app2.start();

        try {
            //wait until countDownLatch reduces to 0.
            countDownLatch.await();
            //As all applications are up, print the message
            System.out.println("All applications are up and running. In thread: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

class CDLab3 implements Runnable {

    private String name;
    private CountDownLatch countDownLatch;

    public CDLab3(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("*** " + name + " was started. ***");
            Lab3.main();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*** " + name + " is up and running. ***");

        //reduce the counter by 1
        countDownLatch.countDown();
    }
}

class CDLab4 implements Runnable {

    private String name;
    private CountDownLatch countDownLatch;

    public CDLab4(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("*** " + name + " was started. ***");
            Lab4.main();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*** " + name + " is up and running. ***");

        //reduce the counter by 1
        countDownLatch.countDown();
    }
}
