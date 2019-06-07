package com.namedlock.leetcode.add2number;

import com.google.common.collect.Lists;

import java.util.List;

public class Solution {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int next=0;

        ListNode pt = new ListNode(0);
        ListNode nextNode = pt;
        while ( l1!=null || l2 !=null){

            int l1val = l1==null?0: l1.val;
            int l2val = l2==null?0: l2.val;

            nextNode.val = (l1val+l2val+next) % 10;
            next=(l1val+l2val+next)/10;

            if(l1!=null){
                l1=l1.next;
            }

            if(l2!=null){
                l2=l2.next;
            }

            if(l1!=null || l2!=null || next>0){
                nextNode.next=new ListNode(next);
            }

            nextNode=nextNode.next;
        }


        return pt;
    }

    public static ListNode buildNode(List<Integer> nodes){

        if(nodes==null){
            return null;
        }

        ListNode listNode = new ListNode(0);
        ListNode pt = listNode;
        for (int i=0; i < nodes.size(); i++){
            pt.val=nodes.get(i);
            if(i!= (nodes.size()-1)){
                pt.next=new ListNode(0);
            }
            pt=pt.next;
        }

        return listNode;
    }

    public static void printListNode(ListNode listNode){
        if(listNode==null){
            System.out.println("null");
        }

        while (listNode!=null){
            System.out.print(","+ listNode.val);
            listNode=listNode.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = buildNode(Lists.newArrayList(5,3,5));
        ListNode l2 = buildNode(Lists.newArrayList(5,6,4));

        ListNode l3 = addTwoNumbers(l1, l2);
        printListNode(l3);
    }
}
