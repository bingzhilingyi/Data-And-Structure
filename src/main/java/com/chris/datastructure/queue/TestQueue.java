package com.chris.datastructure.queue;

import java.util.Random;

public class TestQueue {

    public static void main(String[] args){

        int optCount = 100000;

        Queue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue,optCount);
        System.out.println("ArrayQueue, time: " + time1 + "s");

        Queue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time2 = testQueue(linkedListQueue,optCount);
        System.out.println("LinkedListQueue, time: " + time2 + "s");

        Queue<Integer> loopQueue = new LoopQueue<>();
        double time3 = testQueue(loopQueue,optCount);
        System.out.println("LoopQueue, time: " + time3 + "s");

    }

    private static double testQueue(Queue<Integer> queue,int optCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0 ; i < optCount ; i++){
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0 ; i < optCount ; i++){
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0 ;
    }
}
