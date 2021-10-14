package com.namedlock.sort;

/**
 * BubbleSort: sort an array by repeatedly swapping the adjacent elements if
 * they are in wrong order.
 * <p>
 * Worst and Average Case Time Complexity: O(n^2).
 * Best Case Time Complexity: O(n).
 * Space Complexity: O(1)
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
