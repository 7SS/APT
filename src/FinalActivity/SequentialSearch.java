package FinalActivity;

public class SequentialSearch {

    public  SequentialSearch() {

    }

    public int search(Object[] arr, Object key){

        int arraySize = arr.length;
        for(int i = 0; i < arraySize; i++) {
            if(arr[i] == key) {
                return i;
            }
        }
        return -1;

    }

}
