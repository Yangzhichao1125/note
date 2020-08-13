package com;


import java.util.*;

class ListNode {

      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


public class Solution {




    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {



        ListNode head = new ListNode(-1);
        ListNode temp = head;

        while (l1 != null && l2 != null){
            if(l1.val <= l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        temp.next = l1 == null ? l2 : l1;

        return head.next;
    }


    public int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0)
            return new int[]{};
        if(shorter == longer)
            return new int[]{shorter*k};
        int[] arr = new int[k + 1];
        arr[0] = shorter*k;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i-1] + (longer - shorter);
        }
        return arr;
    }

    public String findLongestWord(String s, List<String> d) {

        String longerst = "";
        for (String str: d) {
            int l = longerst.length();
            int t = str.length();
            if(l > t || (l == t && longerst.compareTo(str)<0))
                continue;
            if(!compare(s,str).equals("")){
                longerst = str;
            }
        }
        return longerst;
    }

    public String compare(String s ,String target){
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == target.charAt(j))
                j++;
        }
        if(j == target.length())
            return target;
        return "";
    }

    public int findKthLargest(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Collections.sort(list);
        Collections.reverse(list);
        return list.get(k-1);
    }

    public void test(){
        List<Integer>[] buckets = new ArrayList[1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int [] arr = new int[] {3,2,1,5,6,4};
        solution.test();
    }

}