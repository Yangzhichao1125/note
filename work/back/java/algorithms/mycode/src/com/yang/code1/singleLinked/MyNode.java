package com.yang.code1.singleLinked;

import java.util.LinkedList;
import java.util.Stack;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/23
 */
public class MyNode {

    Node head ;
    int size  ;

    public void add(int num){
        Node node = new Node(num);
        if(head == null){
            head = node;
        }else{
            Node tmp = head;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        size ++ ;
    }

    public Node lastIndex(int num){
        if(num > size || num <1){
            System.out.println("数值有误");
            return null;
        }
        Node node = head;
        for (int i = 0; i< size - num ;i++){
            node = node.next;
        }
        return node;
    }

    public static void print(Node head){
        Node tmp = head;
        while (tmp != null){
            System.out.println(tmp.data);
            tmp=tmp.next;
        }
    }


    public int size(){
        return size;
    }

    public void backTofront(){
        Node tmp = head;
        Stack<Node> stack = new Stack();
        while (tmp != null){
            stack.push(tmp);
            tmp = tmp.next;
        }

        while(stack.size() != 0){
            System.out.println("stack.pop() = " + stack.pop().data);
        }

    }

    public static Node reverse(Node head){
        if(head.next == null){
            return head;
        }
        Node pre = head;
        Node cur = head.next;
        Node tmp = null;

        while(cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        head.next = null;
        return pre;
    }


    public static void main(String[] args) {
        MyNode myNode = new MyNode();
        myNode.add(1);
        myNode.add(2);
        myNode.add(3);
//        print(myNode.head);
//        Node reverse = reverse(myNode.head);
//        print(reverse);
//        System.out.println("myNode.lastIndex(2) = " + myNode.lastIndex(3).data);
        myNode.backTofront();

    }


}

class Node{
    int data ;
    Node next;
    public Node(int data){
        this.data = data ;
    }
}
