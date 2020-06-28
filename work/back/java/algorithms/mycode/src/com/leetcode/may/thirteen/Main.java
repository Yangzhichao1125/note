package com.leetcode.may.thirteen;

/**
 * This is Description
 *
 * @author yang
 * @date 2020/06/22
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String beg = sc.nextLine();
            String[] s = beg.split(" ");
            int [][] arr = new int[s.length][s.length];
            for (int i = 0; i < s.length; i++) {
                arr[0][i] = Integer.parseInt(s[i]);
            }
            for (int i = 1; i < s.length; i++) {
                String line = sc.nextLine();
                String[] s1 = line.split(" ");
                for (int j = 0; j < s1.length; j++) {
                    arr[i][j] = Integer.parseInt(s1[j]);
                }

            }
            System.out.println(getResult(arr));
        }
    }


    private static int getResult(int [][] n) {
        int count = 0;
        //数组全为1 即可
        for (int i = 0; i < n.length; i++) {
            boolean flag = false;
            for (int j = 0; j < n.length; j++) {
                if(n[i][j] == 0){
                    n[i][j] = 1;
                    flag = true;
                }
                if(n[j][i] == 0){
                    n[j][i] = 1;
                    flag = true;
                }
            }
            if(flag)
                count ++;
        }

        return ++count;

    }





}