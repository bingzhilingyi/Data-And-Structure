package com.chris.datastructure.leetcode.Trie;

import java.util.HashMap;

public class Problem211 {

    class WordDictionary {

        private class Node {
            public Character c;
            public Boolean isWord;
            public HashMap<Character, Node> next;

            public Node(Character c) {
                this.c = c;
                this.isWord = false;
                this.next = new HashMap<>();
            }
        }

        private Node root;
        private int size;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            this.root = new Node(null);
            this.size = 0;
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node(c));
                    size++;
                }
                cur = cur.next.get(c);
            }
            cur.isWord = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word, Node cur,Integer index) {
            if (index==word.length()) {
                return cur.isWord;
            }

            Character c = word.charAt(0);
            if (c.equals('.')) {
                for (Character key : cur.next.keySet()) {
                    if (search(word, cur.next.get(key),index+1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if(cur.next.get(c)==null){
                    return false;
                }else {
                    return search(word,cur.next.get(c),index+1);
                }
            }
        }

        public boolean search(String word) {
            return search(word, root,0);
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}
