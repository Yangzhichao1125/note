package com.leetcode.package10;

public class Demo5 {

    public static void main(String[] args) {
        Demo5 demo5 = new Demo5();
        String str = demo5.longestPalindrome("ccc");
        System.out.println(str);
    }

    public String longestPalindrome(String s)  {
        if(s.length() < 2)
            return s;
        int max = 1;
        String oddStr = null;
        String evenStr = null;
        String maxStr = null;
        String result = s.substring(0,1);
        for (int i = 0; i < s.length()-1 ; i++) {
            oddStr = centerSpread(s,i,i+1);
            evenStr = centerSpread(s,i,i);
            maxStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;

            if(maxStr.length() > max){
                max = maxStr.length();
                result = maxStr;
            }
        }
        return result;
    }

    private String centerSpread(String s, int left, int right)  {
        int l = left,r=right;
        while (l >= 0 && r < s.length()){
          if(s.charAt(l) == s.charAt(r)){
              l--;
              r++;
          }else
              break;
        }
        return s.substring(l+1,r);
    }
}
