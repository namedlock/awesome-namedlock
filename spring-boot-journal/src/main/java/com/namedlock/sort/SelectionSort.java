package com.namedlock.sort;

import java.util.Arrays;

/**
 * SelectionSort: sorts an array by repeatedly finding the minimum element from
 * unsorted part and putting it at the beginning.
 * <p>
 * Time Complexity: O(n^2).
 * Space Complexity: O(1).
 */
public class SelectionSort {
    public static void sort(int array[]) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {

            int min = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            if (i != min) {
                swap(array, i, min);
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 1, 15, 20};
        SelectionSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        SelectionSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10, 2, 7, 3};
        SelectionSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
