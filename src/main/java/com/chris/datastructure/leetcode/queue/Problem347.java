package com.chris.datastructure.leetcode.queue;

import java.util.*;

public class Problem347 {

    private static class Freq{
        public int e,freq;

        public Freq(int e,int freq){
            this.e = e;
            this.freq = freq;
        }
    }

    public static void main(String[] args){
        int[] data = {4,1,-1,2,-1,2,3};
        topKFrequent(data,2);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            if(map.get(num)==null){
                map.put(num,1);
            }else {
                map.put(num,map.get(num)+1);
            }
        }

        PriorityQueue<Freq> quque = new PriorityQueue<>(k,(a,b)-> a.freq-b.freq);
        for(int key:map.keySet()){
            System.out.println(key+":"+map.get(key));
            int frequence = map.get(key);
            if(quque.size()<k){
                quque.add(new Freq(key,frequence));
            }else if(frequence>quque.peek().freq){
                quque.remove();
                quque.add(new Freq(key,frequence));
            }
        }

        int[] ret = new int[k];
        Iterator<Freq> it = quque.iterator();
        int i=0;
        while (it.hasNext()&&i<k){
            ret[i] = it.next().e;
            i++;
        }

        return ret;
    }
}
