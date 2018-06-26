package FinalActivity;

import java.io.*;

public class DirectMerge {

    public DirectMerge(){}

    public void directMerge(File f, boolean reverse) throws IOException {

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
            if (reverse)
                mergeRev(f1, f2, f, longSec, numRec); //greatest first
            else
                merge(f1, f2, f, longSec, numRec); //smallest first
            longSec *= 2;
        }
    }

    private void distribute(File f, File f1, File f2,
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

    private void subsequence(DataInput f,
                                    DataOutput t, int longSec) throws IOException
    {
        int code;
        // writes at t stream, integer data read from f
        for (int j = 1; j <= longSec; j++) {
            code = f.readInt();
            t.writeInt(code);
        }
    }

    private void merge(File f1, File f2, File f,
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
                if (code1 < code2) {  //changes made here < to >
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

    private void mergeRev(File f1, File f2, File f,
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
                if (code1 > code2) {  //changes made here < to >
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
}
