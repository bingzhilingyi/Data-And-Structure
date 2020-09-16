package com.chris.datastructure.leetcode.segmentTree;

public class Problem303 {

    class NumArray {

        private Integer[] tree;
        private int[] data;

        public NumArray(int[] nums) {
            this.data = nums;
            this.tree = new Integer[4*nums.length];

            buildNumArray(0,0,nums.length-1);
        }

        private void buildNumArray(int treeIndex,int l,int r){
            if(l<0||r>=data.length||l>r) return;
            if(l==r){
                tree[treeIndex] = data[l];
                return;
            }

            int mid = l + (r-l)/2;
            int leftChild = leftChild(treeIndex);
            int rightChild = rightChild(treeIndex);

            buildNumArray(leftChild,l,mid);
            buildNumArray(rightChild,mid+1,r);

            tree[treeIndex] = tree[leftChild] + tree[rightChild];
        }

        private int leftChild(int treeIndex){
            return 2*treeIndex+1;
        }

        private int rightChild(int treeIndex){
            return 2*treeIndex+2;
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
            if(i>mid)
                return sumRange(rightChild,mid+1,r,i,j);
            else if(j<=mid)
                return sumRange(leftChild,l,mid,i,j);

            int leftResult = sumRange(leftChild,l,mid,i,mid);
            int rightResult = sumRange(rightChild,mid+1,r,mid+1,j);
            return leftResult+rightResult;
        }
    }
}
