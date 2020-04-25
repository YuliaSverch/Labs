package com.java.ffeks.multithreading.simple;

import com.java.ffeks.base.Lab1;
import com.java.ffeks.base.Lab2;
import com.java.ffeks.base.Lab3;

import java.io.IOException;

class CurrentThreadDemo {

    public static void main(String[] args) {
        Starttasks t = new Starttasks();
        Thread1 thread1 = new Thread1(t);
        Thread2 thread2 = new Thread2(t);
        Thread3 thread3 = new Thread3(t);
        try {
            thread1.t.join();
            thread2.t.join();
            thread3.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("** All threads were executed **");
        System.out.println("Main thread exiting...");
    }
}

class Starttasks {
    String name; // name of thread
    int flag = 0;

    // This is the entry point for thread.
    public synchronized void task1() throws IOException {

        while (flag != 0)
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(name + "Interrupted");
            }
        this.flag = 1;
        Lab1.multithreadingMain();
        System.out.println("************************");
        System.out.println("One exiting.");
        System.out.println("************************");
        notify();
    }

    public synchronized void task2() throws IOException {


        while (flag != 1)
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(name + "Interrupted");
            }
        this.flag = 2;
        Lab2.multithreadingMain();
        System.out.println("************************");
        System.out.println("Two exiting.");
        System.out.println("************************");
        notify();
    }

    public synchronized void task3() {

        while (flag != 2)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(name + "Interrupted");
            }
        this.flag = 3;
        Lab3.main();
        System.out.println("************************");
        System.out.println("Three exiting.");
        System.out.println("************************");
        notify();
    }
}

class Thread1 implements Runnable {
    Thread t;
    Starttasks tasks;

    Thread1(Starttasks tasks) {
        this.tasks = tasks;
        t = new Thread(this, "lab-1");
        t.start();
    }

    public void run() {
        try {
            tasks.task1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Thread2 implements Runnable {
    Starttasks tasks;
    Thread t;

    Thread2(Starttasks tasks) {
        this.tasks = tasks;
        t = new Thread(this, "lab-2");
        t.start();
    }

    public void run() {
        try {
            tasks.task2();
        } catch (IOException exc) {
            System.err.println("Error in lab-2");
        }
    }
}

class Thread3 implements Runnable {
    Starttasks tasks;
    Thread t;

    Thread3(Starttasks tasks) {
        this.tasks = tasks;
        t = new Thread(this, "lab-3");
        t.start();
    }

    public void run() {
        tasks.task3();
    }
}