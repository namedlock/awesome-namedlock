package com.namedlock.sort;

/**
 * QuickSort: every time, it picks one element as a pivot and partitions the
 * array to two parts, where the elements of the first part before pivot are
 * always less or equal to the pivot, and the elements of the second part after.
 * <p>
 * Worst Case Time Complexity: O(n^2).
 * Best and Average Case Time Complexity: O(nlogn).
 * Space Complexity: O(n).
 * <p>
 * Different ways of picking the pivot:
 * <p>
 * - Always pick first element as pivot.
 * - Always pick last element as pivot (implemented below)
 * - Pick a random element as pivot.
 * - Pick median as pivot.
 */
public class QuickSort {
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            exchange(a, i, j);
        }

        exchange(a, lo, j);

        return j;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        if (i <= 0 || j > a.length - 1) {
            return;
        }
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
