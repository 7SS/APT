package FinalActivity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test {

    static int N = 999;
    static int top = 2000;

    public static void main(String[] args) throws Exception{

        File f1 = new File("f1");

        DataOutputStream stream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f1)));


        Random rand = new Random();

        // a)
        for (int j = 1; j <= N; j++)
            stream.writeInt(rand.nextInt(top)+1);
        stream.close();

        write(f1);
        System.out.println("unsorted file");
        System.out.println();

        DataInputStream inFile = new DataInputStream(new BufferedInputStream(
                new FileInputStream(f1)));

        // b)
        ArrayList<Integer> list = new ArrayList<>();
        int in;
        while (inFile.available() > 0){
            in = inFile.readInt();
            list.add(in);
        }

        System.out.println("unsorted arrayList(arrangement)");
        System.out.println(list);
        System.out.println();

        // c)
        int starTimeQSA = (int)System.nanoTime();
        list = new QuickSortArrangement().quickSort(list);
        int endTimeQSA = (int)System.nanoTime();

        System.out.println("arrayList sorted by quickSort method");
        System.out.println(list);
        System.out.println("Time elapsed: " + (endTimeQSA-starTimeQSA) + "ns");
        System.out.println();

        int starTimeQSA2 = (int)System.nanoTime();
        list = new QuickSortArrangement().quickSort(list);
        int endTimeQSA2 = (int)System.nanoTime();

        System.out.println("sorting sorted arrayList by quickSort");
        System.out.println(list);
        System.out.println("Time elapsed: " + (endTimeQSA2-starTimeQSA2) + "ns");
        System.out.println();

        /*
        int starTimeQSA3 = (int)System.nanoTime();
        list = new QuickSortArrangement().quickSortRev(list);
        int endTimeQSA3 = (int)System.nanoTime();

        System.out.println("sorting sorted arrayList by quickSort(reverse)");
        System.out.println(list);
        System.out.println("Time elapsed: " + (endTimeQSA3-starTimeQSA3) + "ns");
        System.out.println();*/

        //sort file with naturalMerge(directMerge)
        System.out.println("Sorting and printing file...\n");
        TimeUnit.SECONDS.sleep(5);
        DirectMerge dm = new DirectMerge();
        boolean reverseOrder = false;
        int starTimeDM = (int)System.nanoTime();
        dm.directMerge(f1, reverseOrder);
        int endTimeDM = (int)System.nanoTime();
        write(f1);
        System.out.println("Time elapsed sorting file: " + (endTimeDM-starTimeDM) + "ns");
        System.out.println();

        System.out.println("Sorting sorted file...");
        TimeUnit.SECONDS.sleep(5);
        int starTimeDM2 = (int)System.nanoTime();
        dm.directMerge(f1, reverseOrder);
        int endTimeDM2 = (int)System.nanoTime();
        write(f1);
        System.out.println("Time elapsed sorting sorted file: " + (endTimeDM2-starTimeDM2) + "ns");
        System.out.println();

        System.out.println("Sorting sorted file(reverse)...");
        TimeUnit.SECONDS.sleep(5);
        reverseOrder = true;
        int starTimeDM3 = (int)System.nanoTime();
        dm.directMerge(f1, reverseOrder);
        int endTimeDM3 = (int)System.nanoTime();
        write(f1);
        System.out.println("Time elapsed sorting sorted file(reverse): " + (endTimeDM3-starTimeDM3) + "ns");
        System.out.println();

        //search x with sequentialSearch
        Scanner input = new Scanner(System.in);
        System.out.println("Type number(sequential search)");
        int xSeq = input.nextInt();
        int foundSeq;
        SequentialSearch sequential = new SequentialSearch();
        int starTimeSeq = (int)System.nanoTime();
        foundSeq = sequential.search(list.toArray(), xSeq);
        int endTimeSeq = (int)System.nanoTime();
        if (foundSeq != -1){
            System.out.println("The value was found at index: " + foundSeq + "\n" +
                                "The time elapsed is: " + (endTimeSeq - starTimeSeq) + "ns");
        }else{
            System.out.println("The value was not found. \nTime elapsed: " +
                                (endTimeSeq - starTimeSeq) + "ns");
        }
        System.out.println();

        //search x with binarySearch
        BinarySearch binary = new BinarySearch();
        System.out.println("Type number(BinarySearch)");
        int xBS = input.nextInt();
        int[] arr = new int[N];
        for (int i=0; i < list.size(); i++)
        {
            arr[i] = list.get(i).intValue();
        }
        int foundBS;

        int starTimeBS = (int)System.nanoTime();
        foundBS = binary.search(arr, 0, arr.length-1, xBS);
        int  endTimeBS = (int)System.nanoTime();
        if (foundBS != -1){
            System.out.println("The value was found at index: " + foundBS + "\n" +
                    "The time elapsed is: " + (endTimeBS - starTimeBS) + "ns");
        }else{
            System.out.println("The value was not found. \nTime elapsed: " +
                    (endTimeBS - starTimeBS + "ns"));
        }


    }

    static void write(File f) {
        int k;
        boolean more = true;
        DataInputStream stream = null;
        try {
            stream = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
            k = 0;
            System.out.println("FILE WITH INT CODES");
            while (more) {
                k++;
                System.out.print(stream.readInt() + " ");
                if (k % 11 == 0)
                    System.out.println();
            }
        } catch (EOFException eof) {
            System.out.println("\n *** End of File ***\n");
            try {
                stream.close();
            } catch (IOException er) {
                er.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
