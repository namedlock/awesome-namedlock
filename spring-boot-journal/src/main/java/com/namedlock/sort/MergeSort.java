package com.namedlock.sort;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int [] array, int n){
        if(n<2){
            return;
        }

        int mid = n/2;
        int [] left = new int[mid];
        int [] right = new int[n-mid];

        for(int i=0;i < mid;i ++){
            left[i]=array[i];
        }

        for (int i=mid; i < n; i++){
            right[i-mid]=array[i];
        }

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(array, left, right, mid, n - mid);

    }

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i=0,j=0,k=0;
        while (i<left&& j< right){
            if(l[i] <= r[j]){
                a[k++]=l[i++];
            }else {
                a[k++]=r[j++];
            }
        }

        while (i<left){
            a[k++]=l[i++];
        }

        while (j<right){
            a[k++]=r[j++];
        }
    }

    public static void main(String[] args) {
        int[] actual = { 5, 1, 6, 2, 3, 4, 8, 7,1000, 88 };
        MergeSort.mergeSort(actual, actual.length);
        System.out.println(Arrays.toString(actual));
    }
}
