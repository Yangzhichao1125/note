package com.yang.code1;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is Description
 *
 * @author yang
 * @date 2020/07/07
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *0 4 8 12
 * L   C   I   R       2n - 2
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 */


//
public class ZClass {

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
    }


    public static String convert(String s, int n) {
        int length = s.length();
        if(length < n ){
            return s;
        }
        char[] arr = s.toCharArray();
        StringBuffer buffer = new StringBuffer();
        boolean flag = true;
        List<StringBuffer> lists = new ArrayList<>();
        for (int i = 0; i < Math.min(n,length); i++) {
            lists.add(new StringBuffer());
        }
        int temp = 0;
        for (int i = 0; i < length; i++) {
            lists.get(temp).append(arr[i]);
            if(i > 0 && i%(n-1) == 0){
                flag =! flag;
                temp += flag ? 1 : -1;
            }

        }

        lists.forEach(item ->{
            buffer.append(item);
        });  
        return buffer.toString();

    }


}
