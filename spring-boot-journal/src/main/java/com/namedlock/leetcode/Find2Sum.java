package com.namedlock.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Find2Sum {

    public static int find(int array[], int input, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int i = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (i++ < array.length) {
            if (array[i] + input == target) {
                return i;
            } else {
                Integer index = map.get(array[i]);
                if (index != null && array[index] + array[i] == target) {
                    return index;
                }
            }

            map.put(array[i], i);

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 6, 9, 10, 5};
//        System.out.println(find(nums, 7, 12));

        int[] ints = find2Sum(nums, 10);
        System.out.println(Arrays.toString(ints));
    }


    public static int[] find(Map<Integer, Integer> source, int[] array, int index, int target) {

        for (int i = index; i < array.length; i++) {
            int remain = target - array[i];
            Integer remainIndex = source.get(remain);
            if (remainIndex != null) {
                return new int[]{i, remainIndex};
            } else {
                source.put(array[i], i);
            }
        }

        return null;
    }

    public static int[] find2Sum(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int[] ints = find(map, array, i, target);
            if (ints != null) {
                return ints;
            }
        }

        return null;
    }
}
