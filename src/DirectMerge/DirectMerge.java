package DirectMerge;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;

// f  =   34 23 12 59 73 44 8 19 28 51

/* In the direct merge method, the sequences of records have lengths that are
 * multiples of two: 1, 2, 4, 8, 16 ... so that the number of passes to be made is fixed,
 * depending on the number of records.
 *
 *
 */

public class DirectMerge {
    //static final int N = 716;
    //static final int TOP = 999;

    public static void main(String[] args) {
        File f = new File("SourceFile");
        DataOutputStream stream = null;

        int[] fArr = {34, 23, 12, 59, 73, 44, 8, 19, 28 ,51};

        try {
            // a sequential file of integer data is generated
            stream = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(f)));
            /*for (int j = 1; j <= N; j++)
                stream.writeInt((int) (1 + TOP * Math.random()));*/
            for (int i = 0 ; i < fArr.length ; i++)
                stream.writeInt(fArr[i]);
            stream.close();
            directMerge(f);
        } catch (IOException e) {
            System.out.println("Input/Output error during sort process");
            e.printStackTrace();
        }
        write(f);

    }

    static void directMerge(File f) throws IOException {
        int longSec;
        int numRec;
        File f1 = new File("AuxFile1");
        File f2 = new File("AuxFile2");
        /*
         * number of records  number obtained by dividing
         * the size of the file by the size
         * of the record: 4.
         */
        numRec = (int) f.length() / 4;
        longSec = 1;
        while (longSec < numRec) {
            distribute(f, f1, f2, longSec, numRec);
            merge(f1, f2, f, longSec, numRec);
            longSec *= 2;
        }
    }

    static void distribute(File f, File f1, File f2,
                           int longSec, int numRec) throws IOException
    {
        int numSec, rest, i;
        DataInputStream stream = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(f)));
        DataOutputStream stream1 = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(f1)));
        DataOutputStream stream2= new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(f2)));
        numSec = numRec / (2 * longSec);
        rest = numRec % (2 * longSec);
        // Distribute sequences of longitude longSec
        for (i = 1; i <= numSec; i++) {
            subsequence(stream, stream1, longSec);
            subsequence(stream, stream2, longSec);
        }
        /*
         * Rest of records in the file are processed
         */
        if (rest > longSec)
            rest -= longSec;
        else {
            longSec = rest;
            rest = 0;
        }
        subsequence(stream, stream1, longSec);
        subsequence(stream, stream2, rest);
        stream.close();
        stream1.close();
        stream2.close();
    }

    private static void subsequence(DataInput f,
                                    DataOutput t, int longSec) throws IOException
    {
        int code;
        // writes at t stream, integer data read from f
        for (int j = 1; j <= longSec; j++) {
            code = f.readInt();
            t.writeInt(code);
        }
    }

    static void merge(File f1, File f2, File f,
                      int lonSec, int numRec) throws IOException {
        int numSec, rest, i, j, k;
        int code1 = 0, code2 = 0;
        numSec = numRec / (2 * lonSec); //numberOfSubsequences
        rest = numRec % (2 * lonSec);
        DataOutputStream stream = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(f)));
        DataInputStream stream1 = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(f1)));
        DataInputStream stream2 = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(f2)));
        // initial codes     //34 23 12 59 73 44 8 19 28 51
        code1 = stream1.readInt();
        code2 = stream2.readInt();
        // loop to control all merge process
        for (int s = 1; s <= numSec + 1; s++) {
            int n1, n2;
            n1 = n2 = lonSec;
            if (s == numSec + 1) { // incomplete sequence process
                if (rest > lonSec)
                    n2 = rest - lonSec;
                else {
                    n1 = rest;
                    n2 = 0;
                }
            }
            i = j = 1;
            while (i <= n1 && j <= n2) {
                int code;
                if (code1 < code2) {
                    code = code1;
                    try {
                        code1 = stream1.readInt();
                    } catch (EOFException e) {
                        ;
                    }
                    i++;
                } else {
                    code = code2;
                    try {
                        code2 = stream2.readInt();
                    } catch (EOFException e) {
                        ;
                    }
                    j++;
                }
                stream.writeInt(code);
            }
            /*
             * Records not processed are direct written
             */
            for (k = i; k <= n1; k++) {
                stream.writeInt(code1);
                try {
                    code1 = stream1.readInt();
                } catch (EOFException e) {
                    ;
                }
            }
            for (k = j; k <= n2; k++) {
                stream.writeInt(code2);
                try {
                    code2 = stream2.readInt();
                } catch (EOFException e) {
                    ;
                }
            }
        }
        stream.close();
        stream1.close();
        stream2.close();
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