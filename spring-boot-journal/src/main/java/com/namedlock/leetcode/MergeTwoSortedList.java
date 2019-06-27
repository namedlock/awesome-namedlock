package com.namedlock.leetcode;

import org.junit.Test;

public class MergeTwoSortedList {
    public ListNode merge(ListNode l1, ListNode l2){

        if(l1==null){
            return l2;
        }

        if(l2==null){
            return l1;
        }

        ListNode head = null;

        while (l1!=null && l2!=null){

            if( l1.val<=l2.val){
                head=append(head, l1);
                l1=l1.next;
            }else {
                head=append(head, l2);
                l2=l2.next;
            }
        }

        while (l1!=null){
            head=append(head, l1);
            l1=l1.next;
        }

        while (l2!=null){
            head=append(head, l2);
            l2=l2.next;
        }

        return head;
    }

    public ListNode merge2(ListNode l1, ListNode l2){
        ListNode head = new ListNode(-1);
        ListNode ptr=head;

        while (l1!=null && l2!=null){
            if( l1.val<=l2.val){
                ptr.next=l1;
                l1=l1.next;
            }else {
                ptr.next=l2;
                l2=l2.next;
            }

            ptr=ptr.next;
        }

        ptr.next=(l1==null? l2:l1);

        return head.next;
    }

    public ListNode append(ListNode head, ListNode l){
        if(head==null){
            return new ListNode(l.val);
        }else {
            ListNode ptr= head;
            while (ptr.next!=null){
                ptr=ptr.next;
            }
            ptr.next=new ListNode(l.val);
        }

        return head;
    }

    public ListNode appendTail(ListNode tail, ListNode l){
        if(tail==null){
            return new ListNode(l.val);
        }else {
            tail.next=new ListNode(l.val);
            tail=tail.next;
        }

        return tail;
    }

    @Test
    public void test(){
        ListNode l1 = buildPtr(new int[]{1,3,4,9});
        ListNode l2 =buildPtr(new int[]{1,4,5,6,8});

        print(l1);
        System.out.println();
        print(l2);
        System.out.println();
        print(merge2(l1, l2));
    }


    public static ListNode buildPtr(int [] array){
        if(array==null){
            return null;
        }

        ListNode h = null;
        ListNode ptr = h;
        for(int i=0; i < array.length; i++){
            int v=array[i];

            if(ptr==null){
                ptr=new ListNode(v);
                h=ptr;
            }else {
                ptr.next=new ListNode(v);
                ptr=ptr.next;
            }
        }

        return h;
    }

    public static void print(ListNode l){
        while (l!=null){
            System.out.print(l.val+"->");
            l=l.next;
        }

    }

}

