package com.chris.datastructure.leetcode.Trie;

import java.util.HashMap;

public class Problem677 {

    class MapSum {

        private class Node{
            public Character c;
            public Integer v;
            public HashMap<Character,Node> next;

            public Node(Character c,Integer v){
                this.c = c;
                this.v = v;
                this.next = new HashMap<>();
            }

            public Node(Character c){
                this(c,0);
            }

            public Node(){
                this(null,0);
            }
        }

        private Node root;

        /** Initialize your data structure here. */
        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            for(int i=0;i<key.length();i++){
                Character c = key.charAt(i);
                if(cur.next.get(c)==null){
                    cur.next.put(c,new Node(c));
                }
                cur = cur.next.get(c);
            }
            cur.v = val;
        }

        public int sum(String prefix) {
            Node cur = root;
            for(int i=0;i<prefix.length();i++){
                Character c = prefix.charAt(i);
                if(cur.next.get(c)==null){
                    return 0;
                }
                cur = cur.next.get(c);
            }
            return sum(cur);
        }

        public int sum(Node cur) {
            int sum = cur.v;
            for(Character c:cur.next.keySet()){
                sum += sum(cur.next.get(c));
            }
            return sum;
        }
    }
}
