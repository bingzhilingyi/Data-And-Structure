package com.chris.datastructure.leetcode.Trie;

import java.util.HashMap;

//实现一个前缀树
public class Problem208 {



    class Trie {

        private class Node{
            public Character c;
            public Boolean isWord;
            public HashMap<Character,Node> next;

            public Node(Character c){
                this.c = c;
                this.isWord = false;
                this.next = new HashMap<>();
            }

            public Node(){
                this(null);
            }
        }

        private Node root;
        private int size;


        /** Initialize your data structure here. */
        public Trie() {
            this.root = new Node();
            this.size = 0;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node cur = root;
            for(int i=0;i<word.length();i++){
                Character c = word.charAt(i);
                if(cur.next.get(c)==null){
                    cur.next.put(c,new Node(c));
                    size++;
                }
                cur = cur.next.get(c);
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node cur = root;
            for(int i=0;i<word.length();i++){
                Character c = word.charAt(i);
                if(cur.next.get(c)==null){
                    return false;
                }
                cur = cur.next.get(c);
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node cur = root;
            for(int i=0;i<prefix.length();i++){
                Character c = prefix.charAt(i);
                if(cur.next.get(c)==null){
                    return false;
                }
                cur = cur.next.get(c);
            }
            return true;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
