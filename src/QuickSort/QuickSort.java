package QuickSort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * unstable implementation of quicksort
 * average case is O(n log n)
 * worst case is O(n)
 */
public class QuickSort {
    private static int partition(int arr[], int left, int right) {

        //get random pivot
        int randPivot = ThreadLocalRandom.current().nextInt(left, right + 1);

        //new position of pivot
        int j = right;

        //move the pivot to right edge of the array
        swap(arr, arr[randPivot], arr[right]);
        right = right-1;

        while (left <= right) {
            if(arr[left]>=arr[j] ){
                    left++; //Accumulate smaller elements to the left
            }else if (arr[right] <= arr[j]){
                right--;
            }
            else {
                swap(arr, left, right);
                right--;
            }
        }

        //Move pivot element to its correct position
        swap(arr, left, j);

        return left;
    }

    private static void swap(int[] arr, int a, int b){
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    private static void quickSort(int arr[], int left, int right) {
        if(left < right){
            int pivot = partition(arr, left, right);

            quickSort(arr, left, pivot-1);
            quickSort(arr, pivot+1, right);
        }
    }

    public static void main (String[]args){
        int[] numbers = new int[10];
        Random rnd = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rnd.nextInt(numbers.length-1)+1;
        }
        System.out.println(Arrays.toString(numbers));
        quickSort(numbers,0,numbers.length-1);
        System.out.println(Arrays.toString(numbers));
    }
}
