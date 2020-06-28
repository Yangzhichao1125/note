package com.leetcode.may.thirteen;

import java.util.Scanner;

/**
 * This is Description
 *
 * @author yang
 * @date 2020/06/22
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        System.out.println(getResult(n,str));
//        System.out.println("a".hashCode());//97
//        System.out.println("z".hashCode());//122
//        System.out.println("A".hashCode());//65
//        System.out.println("Z".hashCode());//90


    }

    private static String getResult(int n, String str) {

        String[] split = str.split("-");
        if(split.length == 1){
            return str;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i < split.length; i++) {
            buffer.append(split[i]);
        }

        String s = buffer.toString();
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if(i%n == 0){
                b.append("-");
            }
            b.append(s.charAt(i));
        }

        String[] s1 = b.toString().split("-");

        StringBuffer result = new StringBuffer();
        result.append(split[0]);
        for (String l:s1 ) {
            if(l.equals(""))
                continue;
            char[] array = l.toCharArray();
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if( 64 < array[i] && array[i] < 91){
                    count ++;//大写
                }else if( 96 < array[i] && array[i] < 123){
                    count --;//小写
                }
            }
            if(count > 0){
                l= l.toUpperCase();
            }else if(count < 0){
                l = l.toLowerCase();
            }
            result.append("-").append(l);
        }

        return result.toString();
    }
}
