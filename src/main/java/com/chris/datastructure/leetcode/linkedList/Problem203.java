package com.chris.datastructure.leetcode.linkedList;

import org.w3c.dom.NodeList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Problem203 {
    public static ListNode removeElements(ListNode head, int val) {
        //solution1
//        ListNode dummyHead = new ListNode(-1,head);
//
//        ListNode prev = dummyHead;
//        while (prev.next!=null){
//            if(prev.next.val==val){
//                prev.next = prev.next.next;
//            }else {
//                prev = prev.next;
//            }
//        }
//        return dummyHead.next;

        //solution2
        if(head==null){
            return null;
        }
        head.next = removeElements(head.next,val);
        if(head.val==val){
            head.next = new ListNode(-1,head.next);
            return head;
        }else {
            return head;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode(int[] arr){
            if(arr==null||arr.length==0){
                throw new IllegalArgumentException("arr can not be empty");
            }
            this.val = arr[0];
            ListNode cur = this;
            for(int i=1;i<arr.length;i++){
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(val+"->");
            ListNode next2 = next;
            while (next2 != null){
                sb.append(next2.val+"->");
                next2 = next2.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    public static void main(String[] args){
        int[] data = {1,2,6,3,4,5,6};
        ListNode head = new ListNode(data);
        System.out.println(head);
        ListNode ret = removeElements(head,6);
        System.out.println(head);
    }
}