package NaturalMerge;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception{

        String nameFile = "numbers.txt";
        FileWriter file_W = null;
        PrintWriter pw;
        int number;

        file_W = new FileWriter(nameFile);
        pw = new PrintWriter(file_W);
        for (int i=0 ; i<999; i++) {
            number = (int) (Math.random() * 2000);
            pw.println(number);
        }
        file_W.close();
        System.out.println("Numbers saved in file(numbers.txt)");

        System.out.println("sorting... This might take a while");
        NaturalMerge nm = new NaturalMerge(nameFile);
        nm.naturalMerge(0, nm.getNumLines()-1);
        System.out.println("The file has been sorted.");
    }

}
