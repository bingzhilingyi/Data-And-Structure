package com.chris.datastructure.leetcode.others;

import java.util.*;

//桶排序
public class Problem347 {

    public static void main(String[] args){
        int[] data = {1,1,1,2,2,3};
        topKFrequent(data,2);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        for(int num:nums){
            if(freq.get(num)==null){
                freq.put(num,1);
            }else {
                freq.put(num,freq.get(num)+1);
            }
        }

        List<Integer>[] bucket = new LinkedList[nums.length+1];
        for(int key:freq.keySet()){
            int frequence = freq.get(key);
            if(bucket[frequence]==null){
                bucket[frequence] = new LinkedList<>();
            }
            bucket[frequence].add(key);
        }

        int[] ret = new int[k];
        int count = 0;
        for(int i=nums.length;i>=0&&count<k;i--){
            if(bucket[i]!=null){
                for(int key:bucket[i]){
                    ret[count] = key;
                    count++;
                    if(count==k){
                        break;
                    }
                }
            }
        }

        return ret;
    }
}
