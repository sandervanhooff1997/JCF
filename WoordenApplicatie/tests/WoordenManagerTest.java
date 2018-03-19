import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import woordenapplicatie.IWoordenManager;
import woordenapplicatie.WoordenManager;

import java.util.*;

public class WoordenManagerTest {

    private IWoordenManager woordenManager;
    private String tekst;

    @Before
    public void setUp() throws Exception {
        tekst = "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n" +
                "\n" +
                "Heb je dan geen hoedje meer\n" +
                "Maak er één van bordpapier\n" +
                "Eén, twee, drie, vier\n" +
                "Hoedje van papier\n" +
                "\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van, hoedje van\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier\n" +
                "\n" +
                "En als het hoedje dan niet past\n" +
                "Zetten we 't in de glazenkas\n" +
                "Een, twee, drie, vier\n" +
                "Hoedje van papier" ;

        testWoordenManager();
    }
    @Test
    public void testWoordenManager() {
        woordenManager = new WoordenManager(tekst);
    }

    @Test
    public void testGetAantal() {
        int aantalWoorden = 68;
        int verschillendeWoorden = 29;
        Assert.assertEquals(aantalWoorden, woordenManager.countTotalWords(tekst));
        Assert.assertEquals(verschillendeWoorden, woordenManager.getDistinctWords().size());
    }

    //OUTCOME: PASSED
    @Test
    public void testSorteer() {

        String firstString = "vier";
        String lastString = "drie";

        Collection<String> woorden = new TreeSet<>();
        woorden = woordenManager.getSortedWords();

        Assert.assertNotNull(woordenManager.getSortedWords().size());

        for (String s : woorden) {
            if(s == "vier"){
                Assert.assertEquals(firstString, s);
                System.out.println(firstString);
            }

            else if (s == "drie"){
                Assert.assertEquals(lastString, s);
                System.out.println(lastString);
            }
        }
    }

    //OUTCOME: PASSED
    @Test
    public void testGetFrequentie() {
        Map<String, Integer> map;
        map = woordenManager.getDuplicateWordsCount();
        Assert.assertNotNull(map);

        int amount = 2;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {

            Assert.assertNotNull(entry.getValue());
            if(entry.getKey() == "hoedje"){
                int frequence = entry.getValue();
                Assert.assertEquals(amount, frequence);
            }
        }
    }

    //OUTCOME: PASSED
    @Test
    public void testConcordatie() {
        TreeMap<String, LinkedList<Integer>> treemap;
        Assert.assertNotNull(woordenManager.getConcordanceWords());
        treemap = woordenManager.getConcordanceWords();

        List<Integer> concordantie = new LinkedList<>();
        concordantie.add(2);
        concordantie.add(2);
        concordantie.add(4);

        for (Map.Entry<String, LinkedList<Integer>> stringInt : treemap.entrySet()) {  // O(n)

            Assert.assertNotNull(stringInt.getValue());
            if(stringInt.getKey() == "van"){
                List<Integer> lijstMetCijfers = stringInt.getValue();
                Assert.assertEquals(concordantie, lijstMetCijfers);
            }
        }
    }
    @Test
    public void testSortText() {
        String firstString = "één";
        String lastString = "geen";
        Assert.assertNotNull(woordenManager.getSortedWords().size());
        Assert.assertEquals(firstString, woordenManager.getSortedWords().get(0));
        Assert.assertEquals(lastString, woordenManager.getSortedWords().get(14));
    }
    @Test
    public void testSortByValue() {

        Map<String, Integer> mapValue = new HashMap<>(25);

        int i = 0;
        while(i < 25){

            mapValue.put( "Woord" + i + 3 * 4 / 2, i + 7 * 2 / 3);
            i+=2;
        }

        Integer valueFirst = 0;

        for(Map.Entry<String, Integer> entry : mapValue.entrySet()) {
            Assert.assertNotNull(entry.getValue());

        }
    }
}
