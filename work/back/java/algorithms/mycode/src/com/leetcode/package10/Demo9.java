package com.leetcode.package10;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * @author yang
 * @date 2019/12/20
 */
public class Demo9 {

    public int isPalindrome(int x) {
        int beg = x;
        int result = 0;
        int tmp = 0;

        while(x != 0){
            tmp = x%10;
            result = result * 10 + tmp ;
            x /=10;
        }
        return  result;
    }

    public static void main(String[] args) {

        Demo9 demo9 = new Demo9();
        int i = demo9.isPalindrome(-121);
        System.out.println("i = " + i);
    }
}
