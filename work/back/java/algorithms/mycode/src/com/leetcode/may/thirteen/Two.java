package com.leetcode.may.thirteen;

/**
 * This is Description
 *
 * @author yang
 * @date 2020/06/09
 */
public class Two {

    public void log(String str){
        try {
              Thread.sleep(100);
              int i = 1/0;
        }catch (Exception e){
              log(e.getMessage());
        }
    }


    public static void main(String[] args) {
        Two two = new Two();
        two.log("log");
    }
}
