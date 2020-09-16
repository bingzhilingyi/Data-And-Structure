package com.chris.datastructure.leetcode.segmentTree;


public class Problem307 {

    static class NumArray {

        private Integer[] tree; //线段树
        private int[] data; //原始数据


        public NumArray(int[] nums) {
            this.data = nums;
            this.tree = new Integer[4*nums.length];
            if(nums.length==0) return;
            buildSegmentTree(0,0,nums.length-1);
        }

        private void buildSegmentTree(int treeIndex,int l,int r){
            if(l==r){
                tree[treeIndex] = data[l];
                return;
            }

            int mid = l + (r-l)/2;
            int leftChild = leftChild(treeIndex);
            int rightChild = rightChild(treeIndex);

            buildSegmentTree(leftChild,l,mid);
            buildSegmentTree(rightChild,mid+1,r);

            tree[treeIndex] = tree[leftChild]+tree[rightChild];
        }

        private int leftChild(int treeIndex){
            return 2*treeIndex+1;
        }

        private int rightChild(int treeIndex){
            return 2*treeIndex+2;
        }

        public void update(int i, int val) {
            int gap = val - data[i];
            data[i] = val;
            update(0,0,data.length-1,i,val);
        }

        private void update(int treeIndex,int l,int r,int i,int val){
            if(l==r){
                tree[treeIndex] = val;
                return;
            }
            int mid = l + (r-l)/2;
            int leftChild = leftChild(treeIndex);
            int rightChild = rightChild(treeIndex);
            if(i<=mid){
                update(leftChild,l,mid,i,val);
            }else{
                update(rightChild,mid+1,r,i,val);
            }

            tree[treeIndex] = tree[leftChild] + tree[rightChild];
        }

        public int sumRange(int i, int j) {
            return sumRange(0,0,data.length-1,i,j);
        }

        public int sumRange(int treeIndex,int l,int r,int i,int j){
            if(l==i&&r==j){
                return tree[treeIndex];
            }

            int mid = l + (r-l)/2;
            int leftChild = leftChild(treeIndex);
            int rightChild = rightChild(treeIndex);
            if(i>mid){
                return sumRange(rightChild,mid+1,r,i,j);
            }else if(j<=mid){
                return sumRange(leftChild,l,mid,i,j);
            }else{
                int leftResult = sumRange(leftChild,l,mid,i,mid);
                int rightResult = sumRange(rightChild,mid+1,r,mid+1,j);
                return leftResult+rightResult;
            }
        }

        public String toString2(){
            StringBuffer sb = new StringBuffer("[");
            for(int i=0;i<tree.length;i++){
                sb.append(tree[i]+",");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args){
        int[] data = {7,2,7,2,0};
        NumArray demo = new NumArray(data);
        System.out.println(demo.toString2());
        demo.update(4,6);
        System.out.println(demo.toString2());
        demo.update(0,2);
        System.out.println(demo.toString2());
        demo.update(0,9);
        System.out.println(demo.toString2());
        System.out.println(demo.sumRange(4,4));
        demo.update(3,8);
        System.out.println(demo.toString2());
        System.out.println(demo.sumRange(0,4));
    }
}
