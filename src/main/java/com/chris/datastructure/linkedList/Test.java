package com.chris.datastructure.linkedList;

public class Test {

    public static void main(String[] args){
        Integer[] data = {1,2,3,4,5};
        long startTime = System.nanoTime();
        LinkedList<Integer> linkedList = new LinkedList<>(data);
        for (int i=0;i<10;i++){
            linkedList.addLast(i);
            System.out.println(linkedList);
        }
//        for (int i=0;i<10;i++){
//            linkedList.removeLast();
//            System.out.println(linkedList);
//        }
        System.out.println(linkedList);
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000000.0) ;
    }
}
