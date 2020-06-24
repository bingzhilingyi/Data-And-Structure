package com.chris.datastructure.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
//        有效字符串需满足：
//
//        左括号必须用相同类型的右括号闭合。
//        左括号必须以正确的顺序闭合。
//        注意空字符串可被认为是有效字符串。
public class Problem20 {

    public static Map<Character,Character> Problem20(){
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        return map;
    }

    public boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = Problem20();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(map.containsKey(c) && !stack.empty()){
                if(stack.pop() != map.get(c)){
                    return false;
                }
            }else{
                stack.push(c);
            }
        }

        return stack.empty();
    }
}
