package com.company;

public class SelectionSort {
    /**
     * selection sort implementation.
     * O(n^2)
     * max 100 data
     * @param arr
     */
    public SelectionSort(int[] arr){
        int min;
        for (int i = 0; i < arr.length; i++) {
            // Assume first element is min
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, min, i);
            System.out.println(arr[i]);// I print the in ascending order
        }
    }

    public static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
