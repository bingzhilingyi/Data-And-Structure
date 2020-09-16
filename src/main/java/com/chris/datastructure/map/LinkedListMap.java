package com.chris.datastructure.map;

import com.chris.datastructure.linkedList.LinkedList;

public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public LinkedListMap.Node next;

        public Node(K key,V value, LinkedListMap.Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key,V value){
            this(key,value,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private Integer size;

    public LinkedListMap(){
        this.dummyHead = new Node();
        this.size = 0;
    }

    private Node getNode(K key){
        Node prev = dummyHead;
        while (prev.next!=null){
            if(prev.next.key.equals(key)){
                return prev.next;
            }
            prev = prev.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node cur = getNode(key);
        if(cur!=null){
            cur.value = value;
            return;
        }
        dummyHead.next = new Node(key,value,dummyHead.next);
        size++;
    }

    @Override
    public V remove(K key) {
        if(isEmpty()){
            throw new IllegalArgumentException("map is empty");
        }
        Node prev = dummyHead;
        while (prev.next!=null){
            if(prev.next.key.equals(key)){
                Node res = prev.next;
                prev.next = res.next;
                res.next = null;
                size--;
                return res.value;
            }
            prev = prev.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key)!=null;
    }

    @Override
    public V get(K key) {
        Node cur = getNode(key);
        if(cur!=null){
            return cur.value;
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node cur = getNode(key);
        if(cur!=null){
            cur.value = value;
        }else {
            throw new IllegalArgumentException(key+" doesn't exist!");
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
