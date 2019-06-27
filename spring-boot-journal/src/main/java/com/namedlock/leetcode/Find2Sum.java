package com.namedlock.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Find2Sum {

    public static int find(int array [], int input, int target){
        if(array==null || array.length==0){
            return -1;
        }

        int i=0;
        Map<Integer, Integer> map=new HashMap<>();
        while (i++<array.length){
            if(array[i]+input==target){
                return i;
            }else {
                Integer index = map.get(array[i]);
                if(index != null && array[index]+ array[i] == target){
                    return index;
                }
            }

            map.put(array[i], i);

        }

        return -1;
    }

    public static void main(String[] args) {
        int [] nums = new int [] {1,3,4,6,9,10,5};
        System.out.println(find(nums, 7, 12));
    }
}
