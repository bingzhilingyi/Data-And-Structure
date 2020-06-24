package com.chris.datastructure.linkedList;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.rmi.UnexpectedException;

public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;
        public Node prev;

        public Node(E e,Node next,Node prev){
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(E e){
            this(e,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }


    private Node dummyHead; //虚拟头节点
    private Node tail; //尾部
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null,null);
        tail = null;
        size = 0;
    }

    public LinkedList(E[] array){
        this();
        for(int i = 0 ; i < array.length ; i++){
            addLast(array[i]);
        }
    }

    /**
     * 获取链表中元素的个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 返回链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 在链表的index(0-based)位置添加新的元素
     * 链表中不常用
     * @param index
     * @param e
     */
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illeagle index.");
        }
        Node prev = dummyHead;
        if(index == size){
            prev = size == 0 ? dummyHead : tail;
            prev.next = new Node(e,prev.next,prev);
            tail = prev.next;
        }else {
            for(int i = 0 ; i < index ; i++){
                prev = prev.next;
            }
            prev.next = new Node(e,prev.next,prev);
        }
        size++;
    }

    /**
     * 向链表头部添加元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 在链表末尾添加新的元素e
     * @param e
     */
    public void addLast(E e){
        add(size,e);
    }

    /**
     * 获得链表的第Index(0-based)个位置的元素
     * 在链表中不常用
     * @param index
     * @return
     */
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        if(index == size-1){
            cur = tail;
        }else {
            for(int i = 0 ; i < index ; i++){
                cur = cur.next;
            }
        }

        return cur.e;
    }

    /**
     * 获得链表的第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获得链表的最后一个元素
     * @return
     */
    public E getLast(){
        return get(size-1);
    }

    /**
     * 修改链表第Index个元素为e
     * 链表中不常用
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否存在e
     * @param e
     * @return
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 从链表中删除指定索引位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        if(index == size-1){
            prev = tail.prev;
            tail = size==1 ? null : prev;
        } else {
            for(int i = 0 ; i < index ; i++){
                prev = prev.next;
            }
        }
        Node retNode = prev.next;

        prev.next = retNode.next;
        retNode.next = null;

        if(prev.next!=null){
            prev.next.prev = prev;
        }
        retNode.prev = null;

        size--;
        return retNode.e;
    }

    /**
     * 从链表中删除第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 从链表中删除最后一个元素
     * @return
     */
    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur+"->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
