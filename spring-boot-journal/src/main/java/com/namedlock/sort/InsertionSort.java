package com.namedlock.sort;

import com.google.common.base.Joiner;

/**
 * InsertionSort: for every element in the array, insert it into sorted sequence iteratively.
 * <p>
 * Time Complexity: O(n^2).
 * Space Complexity: O(1).
 */
public class InsertionSort {

    public static void sort(Integer[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                //exchange arr[j-1] arr[j]
                int tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 5, 8, 6, 1, 9};
        sort(arr);
        System.out.println(Joiner.on(",").join(arr));
    }
}
