import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/* Main coding , decoding */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            IHuffman huffman = new Huffman(false, false, false);
        } catch (IOException e) {
            System.out.println("IOException reading input.");
        }
    }
}

