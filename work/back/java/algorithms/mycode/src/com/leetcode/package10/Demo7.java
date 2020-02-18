package com.leetcode.package10;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *
 * @author yang
 * @date 2019/12/20
 */
public class Demo7 {

    public int reverse(int x) {
        int result = 0;
        int tmp = 0;
        while(x != 0){
            tmp = x%10;
            if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE / 10 && tmp > 7)) return 0;
            if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE / 10 && tmp < -8)) return 0;

            result = result * 10 + tmp ;
            x /=10;
        }
        return result;
    }


    public static void main(String[] args) {
        Demo7 demo7 = new Demo7();
        int re = demo7.reverse(1534236469);
        System.out.println("re = " + re);
        System.out.println(Integer.MAX_VALUE);

    }
}
