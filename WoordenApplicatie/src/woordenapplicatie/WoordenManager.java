package woordenapplicatie;

import java.util.*;

public class WoordenManager implements IWoordenManager{

    private String DEFAULT_TEXT = "";

    public WoordenManager(String DEFAULT_TEXT) {
        this.DEFAULT_TEXT = DEFAULT_TEXT;
    }

    public int countTotalWords(String stringToCount) {
        int wordCount = 0;

        boolean word = false;
        int endOfLine = DEFAULT_TEXT.length() - 1;

        for (int i = 0; i < DEFAULT_TEXT.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetter(DEFAULT_TEXT.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(DEFAULT_TEXT.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(DEFAULT_TEXT.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }

        return wordCount;
    }

    private String[] getSeperatedWords(){
        return DEFAULT_TEXT.split("\\s*(=>|,|\\s)\\s*");
    }

    public ArrayList<String> getDistinctWords(){
        ArrayList<String> distinctWords = new ArrayList<>();

        for (String s : getSeperatedWords()) {
            if(!distinctWords.contains(s)){ //add the word if it isn't added already
                distinctWords.add(s);
            }
        }

        return distinctWords; //return the list you made of distinct words
    }

    public ArrayList<String> getSortedWords(){
        ArrayList<String> sortedWords = getDistinctWords();
        //sortedWords.sort(String::compareToIgnoreCase);
        Collections.sort(sortedWords, Collections.reverseOrder());
        return sortedWords;
    }

    public TreeMap<String, Integer> getDuplicateWordsCount(){
        Map<String, Integer> map = new HashMap<>();

        for (String temp : getSeperatedWords()) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }

        // sort the map order
        return new TreeMap<>(map);
    }

    public TreeMap<String, LinkedList<Integer>> getConcordanceWords(){
        TreeMap<String, LinkedList<Integer>> treemap = new TreeMap<>();

        int i = 0;
        for (String woord : getSeperatedWords()) {

            if (!treemap.containsKey(woord)) {
                LinkedList linkedList = new LinkedList<Integer>();
                linkedList.add(i + 1);
                treemap.put(woord, linkedList);
            } else {
                LinkedList IntList = treemap.get(woord);
                IntList.add(i + 1);
            }
            i++;
        }

        return treemap;

    }
}
