package com.chris.datastructure.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args){
       BST<Integer> bst = new BST<>();
       int[] nums = {22,11,33,1,15,13,24,35};
       for(int num:nums){
           bst.add(num);
       }
       System.out.println();
       System.out.println(bst);

       bst.remove(11);
       bst.levelOrder();
       System.out.println();
       //System.out.println(bst);

        //test removemin
//        Random random = new Random();
//        for(int i=0;i<1000;i++){
//            bst.add(random.nextInt(10000));
//        }
//        List<Integer> l = new LinkedList<>();
//        for(int i=0;i<bst.size();i++){
//            l.add(bst.removeMin());
//        }
//        System.out.println(l);
//        for(int i=1;i<l.size();i++){
//            if(l.get(i-1)>l.get(i)){
//                throw new IllegalArgumentException("Error");
//            }
//        }
//        System.out.println("success");
    }
}
