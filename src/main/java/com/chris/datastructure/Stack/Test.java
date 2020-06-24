package com.chris.datastructure.Stack;

import java.util.Random;

public class Test {

    public static void main(String[] args){
        int opCount = 100000;
        Stack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack,opCount);
        System.out.println("ArrayStack,time:"+time1+" s");

        Stack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack,opCount);
        System.out.println("LinkedListStack,time:"+time2+" s");
    }

    public static double testStack(Stack<Integer> stack,int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i=0;i<opCount;i++){
            stack.push(random.nextInt());
        }
        for(int i=0;i<opCount;i++){
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }
}
