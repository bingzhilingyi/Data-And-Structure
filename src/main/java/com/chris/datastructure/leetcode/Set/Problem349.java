package com.chris.datastructure.leetcode.Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
//求两个数组的交集，相同对象只出现一次，不考虑顺序

public class Problem349 {

    public static void main(String[] args){
        int[] a = {1,2,3,4};
        int[] b = {4,5,3,6};
        Arrays.stream(intersection(a,b)).forEach(num->System.out.println(num));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = Arrays.stream(nums1).parallel().boxed().collect(Collectors.toSet());
        Set<Integer> intersectionSet = new HashSet<Integer>();
        Arrays.stream(nums2).parallel().forEach(num->{
            if(nums1Set.contains(num)){
                intersectionSet.add(num);
            }
        });
        return intersectionSet.parallelStream().mapToInt(Integer::new).toArray();
    }
}
