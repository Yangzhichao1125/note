package com.leetcode.may.thirteen;

/**
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * 题目描述：可以删除一个字符，判断是否能构成回文字符串。
 *
 * 所谓的回文字符串，是指具有左右对称特点的字符串，例如 "abcba" 就是一个回文字符串。
 *
 * @author yang
 * @date 2020/05/13
 */
public class One {

    public static void main(String[] args) {
        String str = "abcba";
        System.out.println(validPalindrome(str));

    }

    public static int [] getIndex(int [] numbers, int target){
        int before = 0;
        int after = numbers.length - 1;
        int temp = 0;
        while ( before < after){
            temp = numbers[before] + numbers[after];
            if(temp < target){
                before++;
            }else if(temp > target){
                after --;
            }else {
                return new int[]{++before,++after};
            }
        }

        return null;
    }

    public static String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int before = 0;
        int after = arr.length - 1;
        String templete = "aeiouAEIOU";
        char temp = ' ';
        while (before < after){
            while(before<after && templete.indexOf(arr[before]) == -1){
                before ++;
            }
            while(before<after && templete.indexOf(arr[after]) == -1){
                after --;
            }
            temp = arr[before];
            arr[before++] = arr[after];
            arr[after--] = temp;

        }

        return new String(arr);
    }

    public static boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return isPalindrome(s,i+1,j) || isPalindrome(s,i,j-1);
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean isPalindrome(String s , int i, int j) {
        while (i<j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;
    }

}
