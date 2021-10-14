package com.namedlock.sort;

import java.util.Arrays;

/**
 * MergeSort: It divides input array in two halves, calls itself for the two
 * halves and then merges the two sorted halves.
 * <p>
 * Time Complexity: O(nLogn) in all 3 cases (worst, average and best).
 * Space Complexity: O(n).
 */
public class MergeSort {
    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (arr == null || l >= r) {
            return;
        }
        int m = (l + r) / 2;

        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {

        int len1 = m - l + 1;
        int len2 = r - m;

        int L[] = new int[len1];
        int R[] = new int[len2];

        for (int i = 0; i < len1; i++) {
            L[i] = arr[l + i];
        }

        for (int j = 0; j < len2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0;
        int j = 0;
        int k = l;
        while (i < len1 && j < len2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < len2) {
            arr[k] = R[j];
            j++;
            k++;
        }

    }


    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 20, 15, 1, 19};
        MergeSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        MergeSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {101, 11};
        MergeSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
