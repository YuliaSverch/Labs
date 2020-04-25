package com.java.ffeks.multithreading.phaser;

import com.java.ffeks.base.Lab3;
import com.java.ffeks.base.Lab4;

import java.time.LocalTime;
import java.util.concurrent.Phaser;

public class PhaserDemo {

    public static final Phaser phaser = new Phaser();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        new PhLab3(phaser, "Lab-3");
        new PhLab4(phaser, "Lab-4");
    }
}

class PhLab3 implements Runnable {

    Phaser phaser;

    public PhLab3(Phaser phaser, String s) {
        this.phaser = phaser;
        new Thread(this, s).start();
    }

    @Override
    public void run() {
        phaser.register();//registering this thread
        System.out.println("---------------------------------------------------------");
        print("after registering");
        System.out.println("---------------------------------------------------------");
        for (int i = 1; i <= 2; i++) {
            try {
                Thread.sleep(5000);
                Lab3.main();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //the barrier point
            System.out.println("---------------------------------------------------------");
            print("before arrive " + i);
            phaser.arriveAndAwaitAdvance();//current thread will wait for others to arrive
            print("after arrive " + i);
            System.out.println("---------------------------------------------------------");
        }
    }

    private void print(String msg) {
        System.out.printf("%-20s: %10s, t=%s, registered=%s, arrived=%s, unarrived=%s phase=%s%n",
                msg,
                Thread.currentThread().getName(),
                LocalTime.now(),
                phaser.getRegisteredParties(),
                phaser.getArrivedParties(),
                phaser.getUnarrivedParties(),
                phaser.getPhase()
        );
    }
}

class PhLab4 implements Runnable {

    Phaser phaser;

    public PhLab4(Phaser phaser, String s) {
        this.phaser = phaser;
        new Thread(this, s).start();
    }

    @Override
    public void run() {
        phaser.register();//registering this thread
        System.out.println("---------------------------------------------------------");
        print("after registering");
        System.out.println("---------------------------------------------------------");
        for (int i = 1; i <= 2; i++) {
            try {
                Thread.sleep(5000);
                Lab4.main();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //the barrier point
            System.out.println("---------------------------------------------------------");
            print("before arrive " + i);
            phaser.arriveAndAwaitAdvance();//current thread will wait for others to arrive
            print("after arrive " + i);
            System.out.println("---------------------------------------------------------");
        }
    }

    private void print(String msg) {
        System.out.printf("%-20s: %10s, t=%s, registered=%s, arrived=%s, unarrived=%s phase=%s%n",
                msg,
                Thread.currentThread().getName(),
                LocalTime.now(),
                phaser.getRegisteredParties(),
                phaser.getArrivedParties(),
                phaser.getUnarrivedParties(),
                phaser.getPhase()
        );
    }
}
