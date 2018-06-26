package HeapSort;

/**
 * MaxHeap version of HeapSort
 * O(n log n)
 */
public class HeapSort {

    public HeapSort(int[] arr){

        int len = arr.length;

        buildMaxHeap(arr, arr.length);

        for (int i = len-1 ; i > 0 ; i--){  //decreases the size of the heap
            int temp = arr[0];              //move the biggest value to last position
            arr[0] = arr[i];
            arr[i] = temp;

            maxHeapify(arr, 1, i);      //find the next biggest value
        }
    }

    /**
     * produces a maxHeap from an arbitrary unordered array
     * the heap is an implementation of a priority queue
     * heap represented by binary tree (sequential)
     * the value of the key is >= of its children
     * @param arr
     * @param heapSize
     */
    private void buildMaxHeap(int[] arr, int heapSize) {
        if (arr == null){
            throw new NullPointerException();
        }
        if (arr.length <= 0 || heapSize <= 0){
            throw new IllegalArgumentException("Illegal");
        }
        if (heapSize > arr.length) {
            heapSize = arr.length;
        }

        //heap complexity is O(log n)
        for (int i = heapSize/2 /*parent*/ ; i > 0 ; i--){  //goes to every
            maxHeapify(arr, i, heapSize);
        }
    }

    /**
     * corrects a single violation of maxHeap property on a root subtrees
     * violation = subtrees value is > than root
     * @param array
     * @param root
     * @param heapSize
     */
    private void maxHeapify(int[] array, int root, int heapSize){
        int l = root * 2;  //left child
        int r = l + 1; //right child
        int largest;    //keep track of the biggest value

        if(l <= heapSize && array[l - 1] > array[root - 1]) { //comparison between left-children and root
            largest = l;    //if left is > than root
        } else {
            largest = root;     //if root is > then left
        }

        if(r <= heapSize && array[r - 1] > array[largest - 1]) { //comparison between right-children and largest
            largest = r;    //if right is > than the largest of either left or root
        }

        if(largest != root) {                           //if the largest value is not already the root
            int temp = array[root - 1];                 //the values are swapped
            array[root - 1] = array[largest - 1];
            array[largest - 1] = temp;
            maxHeapify(array, largest, heapSize);       //goes to the next sub-level of the tree
        }
    }
}
