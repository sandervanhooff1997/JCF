import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/** 
* Huffman Tester. 
* 
* @author Gijs de Wert
* @since <pre>mrt 19, 2018</pre> 
* @version 1.0 
*/ 
public class HuffmanTest {

    private IHuffman hufman;

    @Before
    public void before() throws Exception {
        hufman = new Huffman(true, false, false);
    }

    @After
    public void after() throws Exception {

    }

    /**
    *
    * Method: consoleMenu(Scanner scanner)
    *
    */
    @Test
    public void testConsoleMenu() throws Exception {

        String data = "1";
        int expectedValue = 1;
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            Assert.assertEquals(1, hufman.consoleMenu(scanner));
        } finally {
            System.setIn(stdin);
        }

    }

    /**
    *
    * Method: handlingDecision(Scanner scanner, int decision)
    *
    */
    @Test
    public void testHandlingDecision() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: handleDecodingNewText(Scanner scanner)
    *
    */
    @Test
    public void testHandleDecodingNewText() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: handleEncodingNewText(Scanner scanner)
    *
    */
    @Test
    public void testHandleEncodingNewText() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: handleNewText(Scanner scanner)
    *
    */
    @Test
    public void testHandleNewText() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: IsSameCharacterSet()
    *
    */
    @Test
    public void testIsSameCharacterSet() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: decodeText()
    *
    */
    @Test
    public void testDecodeText() throws Exception {

    }

    /**
    *
    * Method: readFromFile()
    *
    */
    @Test
    public void testReadFromFile() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: encodeText()
    *
    */
    @Test
    public void testEncodeText() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: writeToFile(String encodedText)
    *
    */
    @Test
    public void testWriteToFile() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: buildTree(PriorityQueue<Node> vector)
    *
    */
    @Test
    public void testBuildTree() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: printCodes()
    *
    */
    @Test
    public void testPrintCodes() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals)
    *
    */
    @Test
    public void testCalculateCharIntervals() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: generateCodes(Node node, String s)
    *
    */
    @Test
    public void testGenerateCodes() throws Exception {
    //TODO: Test goes here...
    }


} 
