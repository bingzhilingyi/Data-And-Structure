package com.chris.datastructure.set;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        Random random = new Random(10000);
        long startTime = System.nanoTime();
        Set<Integer> set = new LinkedListSet<>();
        for(int i=0;i<50000;i++){
            set.add(random.nextInt());
            //System.out.println("add:"+i+" size:"+set.getSize());
        }
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000000.0) ;


        long startTime2 = System.nanoTime();
        set = new BSTSet<>();
        for(int i=0;i<50000;i++){
            set.add(random.nextInt());
            //System.out.println("add:"+i+" size:"+set.getSize());
        }
        long endTime2 = System.nanoTime();
        System.out.println((endTime2-startTime2)/1000000000.0) ;
    }
}
