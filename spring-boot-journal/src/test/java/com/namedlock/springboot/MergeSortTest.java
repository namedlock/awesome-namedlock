package com.namedlock.springboot;

public class MergeSortTest {
    public static void mergeSort(int[] arr, int l, int r) {
        if (arr == null || l >= r) {
            return;
        }

        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int len1 = l - m + 1;
        int len2 = r - m;

        int left[] = new int[len1];
        int right[] = new int[len2];

        for (int i = 0; i < len1; i++) {
            left[i] = arr[l + i];
        }

        for (int j = 0; j < len2; j++) {
            right[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0, k = l;
        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < len1) {
            arr[k++] = left[i++];
        }
        while (j < len2) {
            arr[k++] = right[j++];
        }
    }

}
