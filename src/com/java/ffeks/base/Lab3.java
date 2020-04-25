package com.java.ffeks.base;

import java.util.Stack;

public class Lab3 {

    public static void main() {
        new Runner().run();
    }

    static class Runner {

        void run() {
            StackCreator creator = new StackCreator(5);
            Stack<Integer> stack1 = creator.getStack1();
            Stack<Integer> stack2 = creator.getStack2();

            System.out.println("First stack before: ");
            outputStack(stack1);
            System.out.println("Second stack before: ");
            outputStack(stack2);

            Swapper swapper = new Swapper(stack1, stack2);
            swapper.swapStacks();

            System.out.println("First stack after: ");
            outputStack(stack1);
            System.out.println("Second stack after: ");
            outputStack(stack2);
        }

        private static void outputStack(Stack<Integer> stack) {
            stack.stream().map(s -> s + " ").forEach(System.out::print);
            System.out.println();
        }
    }
}

class StackCreator {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    private final int stackSize;

    public StackCreator(int stackSize) {
        this.stackSize = stackSize;
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        fillStacksWithGeneratedRandomVals();
    }

    private void fillStacksWithGeneratedRandomVals() {
        for (int i = 0; i < stackSize; i++) {
            stack1.push((int) (10 + Math.random() * 40));
            stack2.push((int) (20 + Math.random() * 50));
        }
    }

    public Stack<Integer> getStack1() {
        return stack1;
    }

    public Stack<Integer> getStack2() {
        return stack2;
    }
}

class Swapper {

    private final Stack<Integer> stack1;
    private final Stack<Integer> stack2;

    public Swapper(Stack<Integer> stack1, Stack<Integer> stack2) {
        this.stack1 = stack1;
        this.stack2 = stack2;
    }

    public void swapStacks() {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return;
        }

        Integer stackVal1 = stack1.pop();
        Integer stackVal2 = stack2.pop();

        swapStacks();

        stack1.push(stackVal2);
        stack2.push(stackVal1);
    }
}