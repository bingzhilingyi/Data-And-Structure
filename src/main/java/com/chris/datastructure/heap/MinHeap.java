package com.chris.datastructure.heap;

import com.chris.datastructure.Array;

public class MinHeap<E extends Comparable<E>> {
    private Array<E> data;
    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MinHeap(){
        data = new Array<>();
    }

    public MinHeap(E[] arr){
        data = new Array<>(arr);
        //heapify,把普通数组改成最小堆
        //parent(arr.length-1)就是最后一个非叶子节点
        for(int i=parent(arr.length-1);i>=0;i--){
            siftDown(i);
        }
    }

    //返回堆中的元素
    public int size(){
        return data.getSize();
    }

    //返回一个布尔值，表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("index-0 doesn't have a parent");
        }
        return (index-1)/2;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左子节点的索引
    private int leftChild(int index){
        return index*2+1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左子节点的索引
    private int rightChild(int index){
        return index*2+2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int k){
        while (k>0 && data.get(parent(k)).compareTo(data.get(k))>0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    //看堆中最大元素
    public E findMin(){
        if(data.getSize()==0)
            throw new IllegalArgumentException("can not findMax when heap is empty");
        return data.get(0);
    }

    public E extractMin(){
        E ret = findMin();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){
        while (leftChild(k)<size()){
            int j = leftChild(k);
            if(j+1<size() && data.get(j+1).compareTo(data.get(j))<0)
                j++;
            if(data.get(k).compareTo(data.get(j))<0)
                break;
            data.swap(k,j);
            k=j;
        }
    }

    //取出堆中的最小元素，并且替换成元素e
    public E replace(E e){
        E ret = findMin();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
