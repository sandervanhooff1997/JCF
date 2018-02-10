package woordenapplicatie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

public interface IWoordenManager {
    int countTotalWords(String stringToCount);
    ArrayList<String> getDistinctWords();
    ArrayList<String> getSortedWords();
    TreeMap<String, Integer> getDuplicateWordsCount();
    TreeMap<String, LinkedList<Integer>> getConcordanceWords();
}
