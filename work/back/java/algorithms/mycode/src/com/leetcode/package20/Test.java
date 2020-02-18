package com.leetcode.package20;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * This is Description
 *
 * @author yang
 * @date 2020/01/31
 */
public class Test {
    public static void main(String[] args) {
//        boolean b = judgeSquareSum(2);
//        System.out.println(b);
        merge(new int [] {1,2,3,0,0,0},3,new int [] {2,5,6},3);
    }

    public static boolean judgeSquareSum(int c) {
        if(c < 0) return false ;
        int b = (int)Math.sqrt(c);
        int f = 0;
        while(b >= f){
            if(b*b + f*f == c ) return true;
            else if(b*b + f*f < c) f++;
            else b--;
        }
        return false;
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = m+n-1;
        m--;n--;
        while(m > -1 || n > -1){
            if(m < 0){
                nums1[l--] = nums2[n--];
            }else if(n < 0){
                nums1[l--] = nums1[m--];
            }else if(nums1[m] >= nums2[n]){
                nums1[l--] = nums1[m--];
            }else{
                nums1[l--] = nums2[n--];
            }
        }
        Arrays.sort(nums1);
        System.out.println(nums1);
    }

}
