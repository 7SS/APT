package com.company;

import java.util.Arrays;

/**
 * O(n^2)
 * also called bubble sort
 * max 20 data
 */
public class InterchangeSort {
    //modify it to work with strings
    public void InterchangeSort(int[] a){
        for(int i = 0 ; i < a.length ; i++){
            for (int j = i+1 ; j<a.length ; j++){
                if (a[i] > a[j])
                    swap(a, i, j);
            }
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * apply interchange sort to strings
     * @param a
     */
    public void InterchangeSort(String a){
        String[] arrS = a.split("");
        for(int i = 0 ; i < arrS.length ; i++){
            for (int j = i+1 ; j<arrS.length ; j++){
                if (arrS[i].compareTo(arrS[j]) > 0)
                    swap(arrS, i, j);
            }
        }
        System.out.println(Arrays.toString(arrS));
    }

    public static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * swap the characters on the array
     * @param a
     * @param i
     * @param j
     */
    public static void swap(String[] a, int i, int j){
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //exercise 312-12-125-30--45-98
}
