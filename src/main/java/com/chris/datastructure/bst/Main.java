package com.chris.datastructure.bst;

public class Main {

    public static void main(String[] args){
       BST<Integer> bst = new BST<>();
       int[] nums = {5,3,6,8,4,2};
       for(int num:nums){
           bst.add(num);
       }
       bst.postOrder();
       System.out.println();
       System.out.println(bst);
    }
}
