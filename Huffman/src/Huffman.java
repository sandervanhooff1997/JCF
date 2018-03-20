import java.io.*;
import java.util.*;

public class Huffman implements IHuffman {

    private boolean readFromFile;
    private boolean writeToFile;
    private boolean newTextBasedOnOldOne;

    private PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> (o1.value < o2.value) ? -1 : 1);
    private TreeMap<Character, String> codes = new TreeMap<>();
    private String text = "";
    private String encoded = "";
    private String decoded = "";
    private String fileName = "encoded.txt";
    private int ASCII[] = new int[128];
    private String thenThousandWords = "";
    private String oneMillionWords = "";
    private String test = "test";

    public Huffman(boolean readFromFile, boolean writeToFile, boolean newTextBasedOnOldOne) throws IOException {
        this.readFromFile = readFromFile;
        this.writeToFile = writeToFile;
        this.newTextBasedOnOldOne = newTextBasedOnOldOne;

        Scanner scanner = (readFromFile) ? new Scanner(new File(fileName)) : new Scanner(System.in);
        int decision = 1;

        while (decision != -1) { // O(N)
            if (handlingDecision(scanner, decision)) continue;
            decision = consoleMenu(scanner);
        }

        for (int i = 0; i < 10000; i++) {
            test = test + "test";
        }
    }

    @Override
    public int consoleMenu(Scanner scanner) {
        int decision;
        System.out.println("\n<--Menu-->\n" +
                "-> [-1] to exit \n" +
                "-> [1] to enter new text\n" +
                "-> [2] to decode from file");
        decision = Integer.parseInt(scanner.nextLine());
        if (readFromFile) // O(N)
            System.out.println("Decision: " + decision + "\n<!-- Menu End --!>\n");
        return decision;
    }

    @Override
    public boolean handlingDecision(Scanner scanner, int decision) {
        if (decision == 1) { // O(1)
            if (handleNewText(scanner)) return true;
        } else if (decision == 2) {
            if (handleEncodingNewText(scanner)) return true;
        } else if (decision == 3) {
            handleDecodingNewText(scanner);
        } else if (decision == 4) {
            try {
                readFromFile();
            } catch (IOException e) {
                System.out.println("IOException reading from file");
            }
        }
        return false;
    }

    @Override
    public void handleDecodingNewText(Scanner scanner) {
        System.out.println("Enter the text to decode:");
        encoded = scanner.nextLine(); // O(1)
        System.out.println("Text to Decode: " + encoded);
        decodeText();
    }

    @Override
    public boolean handleEncodingNewText(Scanner scanner) {
        System.out.println("Enter the text to encode:");
        text = scanner.nextLine();
        System.out.println("Text to Encode: " + text);

        if (!IsSameCharacterSet()) { //O(1)
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        encodeText();
        return false;
    }

    @Override
    public boolean handleNewText(Scanner scanner) {
        int oldTextLength = text.length();
        System.out.println("Enter the text:");
        text = scanner.nextLine();
        if (newTextBasedOnOldOne && (oldTextLength != 0 && !IsSameCharacterSet())) { //O(1)
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        ASCII = new int[128];
        nodes.clear();
        codes.clear();
        encoded = "";
        decoded = "";
        System.out.println("Text: " + text);
        calculateCharIntervals(nodes, true);
        buildTree(nodes);
        generateCodes(nodes.peek(), "");

        printCodes();
        System.out.println("-- Encoding/Decoding --");
        encodeText();
        decodeText();
        return false;
    }

    @Override
    public boolean IsSameCharacterSet() {
        boolean flag = true;
        for (int i = 0; i < text.length(); i++) //O(N)
            if (ASCII[text.charAt(i)] == 0) {
                flag = false;
                break;
            }
        return flag;
    }

    @Override
    public void decodeText() {
        decoded = "";
        Node node = nodes.peek();
        for (int i = 0; i < encoded.length(); ) { //O(N^2)
            Node tmpNode = node;
            while (tmpNode.left != null && tmpNode.right != null && i < encoded.length()) {
                if (encoded.charAt(i) == '1')
                    tmpNode = tmpNode.right;
                else tmpNode = tmpNode.left;
                i++;
            }
            if (tmpNode != null)
                if (tmpNode.character.length() == 1)
                    decoded += tmpNode.character;
                else
                    System.out.println("Input not Valid");

        }
        System.out.println("Decoded Text: " + decoded);
    }

    @Override
    public void readFromFile() throws IOException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        try {
            Object o = ois.readObject();
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException reading object");
        }
    }

    @Override
    public void encodeText() {
        encoded = "";
        for (int i = 0; i < text.length(); i++) // O(N)
            encoded += codes.get(text.charAt(i));
        System.out.println("Encoded Text: " + encoded);

        if (writeToFile) {
            try {
                writeToFile(encoded);
            } catch (IOException e) {
                System.out.println("IOException reading encoded file.");
            }
        }
    }

    @Override
    public void writeToFile(String encodedText) throws IOException {
        FileOutputStream fout = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(encodedText);
    }

    @Override
    public void buildTree(PriorityQueue<Node> vector) {
        while (vector.size() > 1) // O(N)
            vector.add(new Node(vector.poll(), vector.poll()));
    }

    @Override
    public void printCodes() {
        System.out.println("--- Printing Codes ---");
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v)); // O(N)
    }

    @Override
    public void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals) {
        if (printIntervals) System.out.println("-- intervals --");

        for (int i = 0; i < text.length(); i++) // O(N)
            ASCII[text.charAt(i)]++;

        for (int i = 0; i < ASCII.length; i++) // O(N)
            if (ASCII[i] > 0) {
                vector.add(new Node(ASCII[i] / (text.length() * 1.0), ((char) i) + ""));
                if (printIntervals){
                    //System.out.println("'" + ((char) i) + "' : " + ASCII[i] / (text.length() * 1.0));
                    System.out.println("'" + ((char) i) + "' : " + ASCII[i]);
                }
            }
    }

    @Override
    public void generateCodes(Node node, String s) {
        if (node != null) {
            if (node.right != null) // O(1)
                generateCodes(node.right, s + "1");

            if (node.left != null)
                generateCodes(node.left, s + "0");

            if (node.left == null && node.right == null)
                codes.put(node.character.charAt(0), s);
        }
    }
}
