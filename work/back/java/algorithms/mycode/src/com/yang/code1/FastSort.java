package com.yang.code1;

/**
 * 快速排序
 * （挖坑）
 * （左小右大）
 * （递归）
 *
 * @author yang
 * @date 2019/12/18
 */
public class FastSort {
    public static void main(String[] args) {
        int [] arr = {2,5,8,1,4,3,7,6};
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    public static void sort(int [] s,int l,int r){
        if(l<r){
            int x = s[l],i=l,j=r;
            while(i<j){
                while(i<j && x<s[j])
                    j--;
                if(i<j)
                    s[i++] = s[j];
                while(i<j && x>s[i])
                    i++;
                if(i<j)
                    s[j--] = s[i];
            }
            s[i] = x;
            sort(s,i+1,r);
            sort(s,l,i-1);
        }



    }




}
