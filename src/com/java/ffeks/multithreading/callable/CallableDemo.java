package com.java.ffeks.multithreading.callable;

import com.java.ffeks.base.Lab2;
import com.java.ffeks.base.Lab4;

import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CLab2 lab2 = new CLab2();
        Future<Boolean> future = executorService.submit(lab2);
        if (future.get()) {
            System.out.println();
            new Thread(new CLab4());
        }
        executorService.shutdown();
    }
}

class CLab2 implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        Lab2.Validator validator = new Lab2.Validator();
        return validator.validate();
    }
}

class CLab4 implements Runnable {

    public CLab4() {
        new Thread(this, "lab-4").start();
    }

    @Override
    public void run() {
        Lab4.main();
    }
}
