package com.leetcode.may.thirteen;

import java.util.*;

/**
 * This is Description
 *
 * @author yang
 * @date 2020/06/16
 */
public class Water {

    public static int getNum(String str){
        if(!str.startsWith("0x") || str.length()<2){
            return -1;
        }
        char[] arr = str.toCharArray();
        int leftMove = 0;
        int result = 0;
        for(int i = str.length() - 1; i>1 ; i--){
            switch(arr[i]){
                case('A'):
                    arr[i] = 10;
                    break;
                case('B'):
                    arr[i] = 11;
                    break;
                case('C'):
                    arr[i] = 12;
                    break;
                case('D'):
                    arr[i] = 13;
                    break;
                case('E'):
                    arr[i] = 14;
                    break;
                case('F'):
                    arr[i] = 15;
                    break;
                default:
                    arr[i] -= 48;
                    break;
            }
            //左移
            result = result + (arr[i] << leftMove);

            leftMove += 4;
        }
        return result;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            String str = scan.next();
            System.out.println(Integer.decode(str));
        }
    }

}
