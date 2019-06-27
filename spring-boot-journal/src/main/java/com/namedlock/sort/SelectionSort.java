package com.namedlock.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void sort(int array []){
        if(array==null){
            return;
        }
        int i=0,j=0,min=0;
        int lenght = array.length;
        for (i=0; i < lenght; i ++){

            min=i;

            for (j=i+1; j < lenght; j++){
                if(array[j]< array[min]){
                    min=j;
                }
            }

            if(i!=min){
                swap(array, i, min);
            }
        }
    }

    private static void swap(int [] array, int i, int j) {
        int tmp = array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 1, 15, 20};
        SelectionSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        SelectionSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10,2,7,3};
        SelectionSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
