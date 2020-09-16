package com.chris.datastructure.linkedList;

import com.chris.datastructure.map.LinkedListMap;

public class Main {

    public static void main(String[] args){
        String[] data = {"a","b","c","d","a"};
        LinkedListMap<String,Integer> linkedListMap = new LinkedListMap<>();
        for(int i=0;i<data.length;i++){
            if(linkedListMap.contains(data[i])){
                Integer frequence = linkedListMap.get(data[i]);
                linkedListMap.set(data[i],++frequence);
            }else {
                linkedListMap.add(data[i],1);
            }

        }
        System.out.println(linkedListMap.getSize());
        System.out.println(linkedListMap.get("a"));
    }
}
