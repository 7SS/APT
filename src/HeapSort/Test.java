package HeapSort;

import java.util.Arrays;
import java.util.Random;

/**
 * Class to test both implementations of HeapSort
 */
public class Test {

    public static void main(String[] args) {

        int[] numbers = new int[10]; //set size of input

        Random rnd = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rnd.nextInt(100)+1; //array filled with random elements
        }

        System.out.println(Arrays.toString(numbers)+" {Original}");
        new HeapSort(numbers);
        System.out.println(Arrays.toString(numbers)+" {MaxHeap}");
        new HeapSort2(numbers);
        System.out.println(Arrays.toString(numbers)+" {MinHeap}");
    }
}
