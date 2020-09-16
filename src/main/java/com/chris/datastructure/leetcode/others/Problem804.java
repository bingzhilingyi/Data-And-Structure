package com.chris.datastructure.leetcode.others;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem804 {

    public static void main(String[] args){
        long startTime = System.nanoTime();
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(uniqueMorseRepresentations(words));
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000000.0);
    }

    private static int uniqueMorseRepresentations(String[] words) {
        String[] morseData = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> res = new HashSet<>();
        for(int i=0;i<words.length;i++){
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<word.length();j++){
                sb.append(morseData[word.charAt(j)-'a']);
            }
            res.add(sb.toString());
        }
        return res.size();
    }
}
