package FinalActivity;

import java.util.ArrayList;
import java.util.List;

public class QuickSortArrangement {

    public QuickSortArrangement() { }

    public ArrayList<Integer> quickSort(ArrayList<Integer> input){
        if(input.size() <= 1){
            return input;
        }

        int middle = (int) Math.ceil((double)input.size() / 2);
        int pivot = input.get(middle);

        ArrayList<Integer> less = new ArrayList<Integer>();
        ArrayList<Integer> greater = new ArrayList<Integer>();

        for (int i = 0; i < input.size(); i++) {
            if(input.get(i) <= pivot){
                if(i == middle){
                    continue;
                }
                less.add(input.get(i));
            }
            else{
                greater.add(input.get(i));
            }
        }

        return concatenate(quickSort(less), pivot, quickSort(greater));
    }

    public ArrayList<Integer> quickSortRev(ArrayList<Integer> input){
        if(input.size() <= 1){
            return input;
        }

        int middle = (int) Math.ceil((double)input.size() / 2);
        int pivot = input.get(middle);

        ArrayList<Integer> less = new ArrayList<Integer>();
        ArrayList<Integer> greater = new ArrayList<Integer>();

        for (int i = input.size()-1; i >= 0; i--) {
            if(input.get(i) >= pivot){
                if(i == middle){
                    continue;
                }
                less.add(input.get(i));
            }
            else{
                greater.add(input.get(i));
            }
        }

        return concatenate(quickSort(less), pivot, quickSort(greater));
    }

    private ArrayList<Integer> concatenate(ArrayList<Integer> less, int pivot, ArrayList<Integer> greater){

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < less.size(); i++) { //changes made here < to >
            list.add(less.get(i));
        }

        list.add(pivot);

        for (int i = 0; i < greater.size(); i++) { //changes made here < to >
            list.add(greater.get(i));
        }

        return list;
    }

    private ArrayList<Integer> concatenateRev(ArrayList<Integer> less, int pivot, ArrayList<Integer> greater){

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = less.size()-1; i > 0; i--) { //changes made here < to >
            list.add(less.get(i));
        }

        list.add(pivot);

        for (int i = greater.size()-1; i > 0; i--) { //changes made here < to >
            list.add(greater.get(i));
        }

        return list;
    }

}
