package com.chris.datastructure.leetcode.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

//求两个数组间的交集，重复几次就显示几次
public class Problem350 {

    public static void main(String[] args){
        int[] a = {1,2,2,1};
        int[] b = {2,2};
        Arrays.stream(intersect(a,b)).forEach(n->System.out.println(n));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> nums1Map = new TreeMap<>();
        for(int num:nums1){
            if(nums1Map.containsKey(num)){
                nums1Map.put(num,nums1Map.get(num)+1);
            }else {
                nums1Map.put(num,1);
            }
        }

        ArrayList<Integer> re = new ArrayList<>();
        for(int num:nums2){
            if(nums1Map.containsKey(num)){
                re.add(num);
                nums1Map.put(num,nums1Map.get(num)-1);
                if(nums1Map.get(num)==0){
                    nums1Map.remove(num);
                }
            }
        }

        return re.stream().mapToInt(Integer::new).toArray();
    }
}
