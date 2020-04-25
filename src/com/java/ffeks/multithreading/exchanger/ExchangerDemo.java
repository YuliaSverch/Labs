package com.java.ffeks.multithreading.exchanger;

import com.java.ffeks.base.Lab1;

import java.io.IOException;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Task1("lab-1", exchanger);
        new Task2("lab-2", exchanger);
    }
}

class Task1 implements Runnable {

    String threadName;
    String str;

    Exchanger<String> ex;

    Task1(String n, Exchanger<String> exchanger) {
        threadName = n;
        ex = exchanger;
        str = "";
        new Thread(this, threadName).start();
    }

    @Override
    public void run() {

        try {
            Lab1.Runner runner = new Lab1.Runner();
            String result = runner.getReplacedText();
            str = ex.exchange(result);
        } catch (InterruptedException | IOException exc) {
            System.out.println(exc);
        }
        System.out.println("-------------------");
    }
}

class Task2 implements Runnable {

    String threadName;
    String str;

    Exchanger<String> ex;

    Task2(String n, Exchanger<String> exchanger) {
        threadName = n;
        ex = exchanger;
        str = "";
        new Thread(this, threadName).start();
    }

    @Override
    public void run() {
        try {
            str = ex.exchange("");
            ExchLab2.Validator validator = new ExchLab2.Validator();
            validator.isIpMatches(str);

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        System.out.println();
        System.out.println("-------------------------");
    }

}

