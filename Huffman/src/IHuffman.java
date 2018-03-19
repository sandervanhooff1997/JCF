import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public interface IHuffman {
    int consoleMenu(Scanner scanner);

    boolean handlingDecision(Scanner scanner, int decision);

    void handleDecodingNewText(Scanner scanner);

    boolean handleEncodingNewText(Scanner scanner);

    boolean handleNewText(Scanner scanner);

    boolean IsSameCharacterSet();

    void decodeText();

    void readFromFile () throws IOException;

    void encodeText();

    void writeToFile(String encodedText) throws IOException;

    void buildTree(PriorityQueue<Node> vector);

    void printCodes();

    void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals);

    void generateCodes(Node node, String s);
}
