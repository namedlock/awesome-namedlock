package com.namedlock.sort;

import java.util.Arrays;

public class HeapSort {
    public static void sort(int[] arr) {
        if(arr== null){
            return;
        }
        int n = arr.length;
        for(int i=n/2 -1; i >=0; i --){
            heapify(arr, n, i);
        }

        for (int i= n-1; i >=0; i--){
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }

    }

    private static void heapify(int arr[], int n, int i) {

        int largest = i;
        int left=i*2+1, right=i*2+2;
        if(left<n && arr[left] > arr[largest]){
            largest=left;
        }

        if(right<n && arr[right] > arr[largest]){
            largest=right;
        }

        if(largest!=i){
            swap(arr, largest, i);
            heapify(arr, n, largest);
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 133, 7, 5, 9,9,8, 20, 15, 100};
        HeapSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        HeapSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        HeapSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
