package com.java.ffeks.multithreading.barrier;

import com.java.ffeks.base.Lab3;
import com.java.ffeks.base.Lab4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo implements Runnable {

    public static CyclicBarrier newBarrier = new CyclicBarrier(2, new ReachedBarrierAction());

    public static void main(String[] args) {
        // parent thread
        CyclicBarrierDemo barrierDemo = new CyclicBarrierDemo();

        Thread thread = new Thread(barrierDemo);
        thread.start();
    }

    @Override
    public void run() {
        // objects on which the child thread has to run
        BLab3 lab3 = new BLab3();
        BLab4 lab4 = new BLab4();

        // creation of child thread
        Thread t1 = new Thread(lab3);
        Thread t2 = new Thread(lab4);

        // moving child thread to runnable state
        t1.start();
        t2.start();

        try {
            CyclicBarrierDemo.newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        // Resetting the newBarrier
        newBarrier.reset();
        System.out.println("** Barrier reset successful. **");
    }
}

class BLab3 implements Runnable {
    @Override
    public void run() {
        try {
            Lab4.main();
            Thread.sleep(2000);
            CyclicBarrierDemo.newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class BLab4 implements Runnable {
    @Override
    public void run() {
        System.out.println("--> Is the barrier broken? - " + CyclicBarrierDemo.newBarrier.isBroken());
        try {
            Lab3.main();
            Thread.sleep(2000);
            CyclicBarrierDemo.newBarrier.await(3000, TimeUnit.MILLISECONDS);
            System.out.println("Number of parties waiting at the barrier " +
                    "at this point = " + CyclicBarrierDemo.newBarrier.getNumberWaiting());
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}

class ReachedBarrierAction implements Runnable {
    @Override
    public void run() {
        System.out.println("**************************");
        System.out.println("barrier was reached.");
        System.out.println("**************************");
    }
}