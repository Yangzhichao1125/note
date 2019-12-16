package com.leetcode;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/11/28
 */
public class Demo3 {

    public static void main(String[] args) {
        int length = lenOf("abcdefahbe");
        System.out.println(length);
    }
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int leftIndex = 0;
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            for (int j = leftIndex; j < i; j++) {
                if(array[j] == array[i]){
                    maxLength = Math.max(maxLength,j-leftIndex);
                    leftIndex = j+1;
                    break;
                }
            }
        }
        return Math.max(array.length - leftIndex, maxLength);
    }

    public static int lenOf(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray();
        int leftIndex = 0;
        for (int j = 0; j < chars.length; j++) {
            for (int i = leftIndex; i < j; i++) {
                if (chars[i] == chars[j]) {
                    maxLength = Math.max(maxLength, j - leftIndex);
                    leftIndex = i + 1;
                    break;
                }
            }
        }
        return Math.max(chars.length - leftIndex, maxLength);
    }
}
