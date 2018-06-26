package HeapSort;

/**
 * MinHeap version of Heapsort]
 * O(n log n)
 */
public class HeapSort2 {

    public HeapSort2(int[] arr){

        int len = arr.length;

        buildMinHeap(arr, arr.length);

        for (int i = len-1 ; i > 0 ; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            minHeapify(arr, 1, i);
        }
    }

    /**
     * produces a minHeap from an arbitrary unordered array
     * the heap is an implementation of a priority queue
     * heap represented by binary tree (sequential)
     * the value of the key is <= of its children
     * @param arr
     * @param heapSize
     */
    private void buildMinHeap(int[] arr, int heapSize) {
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
        for (int i = heapSize/2 /*parent*/ ; i > 0 ; i--){
            minHeapify(arr, i, heapSize);
        }
    }

    /**
     * corrects a single violation of minHeap property on a root subtrees.
     * violation = subtrees are < than the root
     * @param array
     * @param root
     * @param heapSize
     */
    private void minHeapify(int[] array, int root, int heapSize){
        int l = root * 2;  //left child
        int r = l + 1; //right child
        int least;    //keep track of the next value to be moved

        if(l <= heapSize && array[l - 1] < array[root - 1]) {
            least = l;
        } else {
            least = root;
        }

        if(r <= heapSize && array[r - 1] < array[least - 1]) {
            least = r;
        }

        if(least != root) {
            int temp = array[root - 1];
            array[root - 1] = array[least - 1];
            array[least - 1] = temp;
            minHeapify(array, least, heapSize);
        }
    }
}
