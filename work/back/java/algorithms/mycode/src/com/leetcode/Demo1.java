package com.leetcode;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 * @author yang
 * @date 2019/11/27
 *
 *
 * O n2  O1
 *
 * 2:map  key value
 *
 *
 *
 *
 */
public class Demo1 {

    public static void main(String[] args) {
        int [] arr = {2,7,15,9};
        int target = 11;
        int [] result = getIndex(arr,target);
        System.out.println("result "+result[0]+"\t"+result[1]);
    }


    private static int[] getIndex(int[] arr, int target) {
        int[] result =  {-1,-1};
        if(arr != null && arr.length>1){
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if(map.containsKey(target - arr[i])){
                    result[0] = i;
                    result[1] = map.get(target-arr[i]);
                    return result;
                }
                map.put(arr[i],i);
            }

        }

        return result;
    }

}
