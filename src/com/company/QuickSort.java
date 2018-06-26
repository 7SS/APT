package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    private String output;

    public String getOutput(){
        return output;
    }

    public QuickSort(int[] arr){
        Random random = new Random();
        int left = random.nextInt(arr.length-1);
        sort(arr,left, arr.length - 1);
    }

    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    private int partition(int arr[], int left, int right) {
        //Random pivot of the array
        //int rand = ThreadLocalRandom.current().nextInt(0, arr.length-1);
        swap(arr[left], arr[left]);
        int pivot = arr[left];
        int low = left-1;
        int high = right;

        while(low <= high) {
            while(arr[high] > pivot){
                high = high -1;
            }
            while(low <= high && arr[low] <= pivot){
                low = low + 1;
            }
            if (low <= high){
                swap(arr[low], arr[high]);
                low = low + 1;
                high = high - 1;
            }
        }

        swap(arr[left], arr[high]);

        output += "\nPivot: " + pivot + " Index: " + left + "\n" + Arrays.toString(arr);
        System.out.println(output);

        return high;
    }

    private void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
        private void sort ( int arr[], int left, int right)
        {
            if (left < right) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
                int pivot = partition(arr, left, right);

                // Recursively sort elements before
                // partition and after partition
                sort(arr, left, pivot - 1);
                sort(arr, pivot + 1, right);
            }
        }
}
