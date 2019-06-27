package com.namedlock.leetcode;

import org.junit.Test;

public class RemoveNthNode {

    public ListNode remove(ListNode l, int n){

        ListNode dummy = new ListNode(-1);
        dummy.next=l;

        ListNode first = dummy;
        ListNode second = dummy;

        for(int i=0; i <= n+1 && first!=null; i ++){
            first=first.next;
        }

        while (first!=null){
            first=first.next;
            second=second.next;
        }

        ListNode nn= second.next;
        second.next=nn.next;

        return dummy.next;

    }

    @Test
    public void test(){

        ListNode l= MergeTwoSortedList.buildPtr(new int[] {1,2,4,6,7,8});
        MergeTwoSortedList.print(l);
        System.out.printf("\n");
        MergeTwoSortedList.print(remove(l, 10));

    }

}
