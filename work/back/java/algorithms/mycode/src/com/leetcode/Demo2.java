package com.leetcode;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/11/28
 */
public class Demo2 {
    public static void main(String[] args) {
        ListNode node = new ListNode(2);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        node.next = node1;
        node1.next = node2;

        ListNode node11 = new ListNode(5);
        ListNode node12 = new ListNode(6);
        ListNode node13 = new ListNode(4);
        node11.next=node12;
        node12.next=node13;


        ListNode listNode = addTwoNumbers(node, node11);
        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode curent = result;
        int temp = 0;
        int num1 = 0;
        int num2 = 0;
        while(l1 != null || l2 != null || temp != 0){
            num1 = l1!=null ? l1.val : 0;
            num2 = l2!=null ? l2.val : 0;
            ListNode newNode = new ListNode((num1+num2+temp)%10);
            curent.next = newNode ;
            curent = newNode;
            temp = (num1 + num2 + temp)/10;
            if(l1 != null) l1=l1.next;
            if(l2 != null) l2=l2.next;
        }

        return result.next;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
