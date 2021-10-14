package com.namedlock.search;

public class BinarySearch {


    public static int search(int array[], int left, int right, int x) {

        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if (array[mid] == x) {
            return mid;
        }

        if (array[mid] > x) {
            return search(array, left, mid - 1, x);
        }
        return search(array, mid + 1, right, x);

    }

    /**
     * iteratively
     *
     * @param array
     * @param x
     * @return
     */
    public static int search(int[] array, int x) {
        if (array == null) {
            return -1;
        }
        int l = 0;
        int r = array.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (array[m] == x) {
                return m;
            }

            if (array[m] > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 10, 15, 20};
        System.out.println(BinarySearch.search(arr, 20));
        System.out.println(BinarySearch.search(arr, 0, arr.length - 1, 7));
    }
}
