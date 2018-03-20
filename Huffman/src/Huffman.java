import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Huffman implements IHuffman {
    private PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> (o1.value < o2.value) ? -1 : 1);
    private TreeMap<Character, String> codes = new TreeMap<>();
    private String text = "";
    private String encoded = "";
    private String decoded = "";
    private String fileName = "encoded.txt";
    private int ASCII[] = new int[128];

    public Huffman() throws IOException {
        // create scanner from file / create scanner from input
        Scanner scanner = new Scanner(System.in);

        // determine the decision
        int decision = 0;
        while (decision != -1) {
            // if the decision is either 1 or 2 continue
            if (handlingDecision(scanner, decision)) continue;
            decision = consoleMenu(scanner);
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
//        System.out.println("Decision: " + decision + "\n<!-- Menu End --!>\n");
        return decision;
    }

    @Override
    public boolean handlingDecision(Scanner scanner, int decision) {
        // create new huffman from text
        if (decision == 1) {
            if (handleNewText(scanner)) return true;
        }
//        else if (decision == 2) {
//            if (handleEncodingNewText(scanner)) return true;
//        } else if (decision == 3) {
//            handleDecodingNewText(scanner);
//        }
        // create huffman from file
        else if (decision == 2) {
            try {
                if (readFromFile()) return true;
            } catch (IOException e) {
                System.out.println("IOException reading from file");
            }
        }
        return false;
    }

    @Override
    public void handleDecodingNewText(Scanner scanner) {
        System.out.println("Enter the text to decode:");
        encoded = scanner.nextLine();
        System.out.println("Text to Decode: " + encoded);
        decodeText();
    }

    @Override
    public boolean handleEncodingNewText(Scanner scanner) {
        System.out.println("Enter the text to encode:");
        text = scanner.nextLine();
        System.out.println("Text to Encode: " + text);

        if (!IsSameCharacterSet()) {
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        encodeText();
        return false;
    }

    @Override
    public boolean handleNewText(Scanner scanner) {
//        int oldTextLength = text.length();
        System.out.println("Enter the text:");
        text = scanner.nextLine();
//        if (newTextBasedOnOldOne && (oldTextLength != 0 && !IsSameCharacterSet())) {
//            System.out.println("Not Valid input");
//            text = "";
//            return true;
//        }

        // reset all values
        ASCII = new int[128];
        nodes.clear();
        codes.clear();
        encoded = "";
        decoded = "";

        // display text to user
        System.out.println("Text: " + text);

        // calculate char intervals
        calculateCharIntervals(nodes, true);

        // build the tree from the nodes
        buildTree(nodes);

        // generate tree node codes (0/1)
        generateCodes(nodes.peek(), "");

        printCodes();

        // show the encoded text and decoded text
        System.out.println("-- Encoding/Decoding --");
        encodeText();
        decodeText();
        return false;
    }

    @Override
    public boolean IsSameCharacterSet() {
        boolean flag = true;
        for (int i = 0; i < text.length(); i++)
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
        for (int i = 0; i < encoded.length(); ) {
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
    public boolean readFromFile() throws IOException {
        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(fileName)){
            while (scanner.hasNextLine()){
                //process each line in some way
                System.out.println(scanner.nextLine());
            }
            return true;
        }catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void encodeText() {
        encoded = "";
        for (int i = 0; i < text.length(); i++)
            encoded += codes.get(text.charAt(i));
        System.out.println("Encoded Text: " + encoded);

        try {
            writeToFile(encoded);
        } catch (IOException e) {
            System.out.println("IOException reading encoded file.");
        }
    }

    @Override
    public void writeToFile(String encodedText) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(encodedText);
        writer.close();
    }

    @Override
    public void buildTree(PriorityQueue<Node> vector) {
        // remove the top 2 nodes and re-add this in 1 node with 2 child nodes
        while (vector.size() > 1)
            vector.add(new Node(vector.poll(), vector.poll()));
    }

    @Override
    public void printCodes() {
        System.out.println("--- Printing Codes ---");
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v));
    }

    @Override
    public void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals) {
        if (printIntervals) System.out.println("-- intervals --");

        // count the interval of each char in the 'text' string and add the frequency to the letter (key)
        for (int i = 0; i < text.length(); i++)
            ASCII[text.charAt(i)]++;

        // create nodes for each distinct letter in 'ASCII[]'
        for (int i = 0; i < ASCII.length; i++)
            if (ASCII[i] > 0) {
                // add the new node to the PriorityQueue of nodes
                vector.add(new Node(ASCII[i] / text.length(), ((char) i) + ""));

                if (printIntervals){
                    System.out.println("'" + ((char) i) + "' : " + ASCII[i]);
                }
            }
    }

    @Override
    public void generateCodes(Node node, String s) {
        if (node != null) {
            // generate the code for the right node if there is one
            if (node.right != null)
                generateCodes(node.right, s + "1");

            // generate the code for the left node if there is one
            if (node.left != null)
                generateCodes(node.left, s + "0");

            // if the node has no childs left put the final string to the last node
            if (node.left == null && node.right == null)
                codes.put(node.character.charAt(0), s);
        }
    }
}
