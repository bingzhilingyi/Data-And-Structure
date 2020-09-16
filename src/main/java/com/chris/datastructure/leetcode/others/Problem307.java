package com.chris.datastructure.leetcode.others;

public class Problem307 {
    static class NumArray {

        private Integer[] data;
        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            data = new Integer[nums.length];
            for(int i=0;i<nums.length;i++){
                if(i>0)
                    data[i] = nums[i]+data[i-1];
                else
                    data[i] = nums[i];
            }
        }

        public void update(int i, int val) {
            int gap = val - nums[i];
            nums[i] = val;
            for(int id=i;id<data.length;id++){
                data[id] = gap + data[id];
            }
            for(int n=0;n<data.length;n++){
                System.out.print(data[n]+",");
            }
            System.out.println();
        }

        public int sumRange(int i, int j) {
            if(i==0)
                return data[j];
            else
                return data[j] - data[i-1];
        }
    }

    public static void main(String[] args){
        int[] data = {7,2,7,2,0};
        NumArray demo = new NumArray(data);
        demo.update(4,6);
        demo.update(0,2);
        demo.update(0,9);
        System.out.println(demo.sumRange(4,4));
        demo.update(3,8);
        System.out.println(demo.sumRange(0,4));
    }
}
