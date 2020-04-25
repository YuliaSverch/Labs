package com.java.ffeks.multithreading.semaphores;

import com.java.ffeks.base.Lab1;
import com.java.ffeks.base.Lab2;
import com.java.ffeks.base.Lab3;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class SemaphoresDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        new SLab1(semaphore,"lab-1");
        new SLab2(semaphore,"lab-2");
        new SLab3(semaphore,"lab-3");
    }
}

class SLab1 implements Runnable {

    Semaphore semaphore;
    String threadName;

    SLab1(Semaphore s, String n) {
        semaphore = s;
        threadName = n;
        new Thread(this, threadName).start();
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();
            System.out.println("****" + threadName + "****");
            Lab1.multithreadingMain();
        } catch (InterruptedException | IOException exc) {
            System.out.println(exc);
        }
        System.out.println("----------------------");
        semaphore.release();
    }
}

class SLab2 implements Runnable {

    Semaphore semaphore;
    String threadName;

    SLab2(Semaphore s, String n) {
        semaphore = s;
        threadName = n;
        new Thread(this, threadName).start();
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();
            System.out.println("****" + threadName + "****");
            Lab2.multithreadingMain();
        } catch (InterruptedException | IOException exc) {
            System.out.println(exc);
        }
        System.out.println("----------------------");
        semaphore.release();
    }
}

class SLab3 implements Runnable {

    Semaphore semaphore;
    String threadName;

    SLab3(Semaphore s, String n) {
        semaphore = s;
        threadName = n;
        new Thread(this, threadName).start();
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();
            System.out.println("****" + threadName + "****");
            Lab3.main();
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        System.out.println("----------------------");
        semaphore.release();
    }
}
