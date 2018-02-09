package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

 private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n" +
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
         "Hoedje van papier";

 @FXML
 private Button btAantal;
 @FXML
 private TextArea taInput;
 @FXML
 private Button btSorteer;
 @FXML
 private Button btFrequentie;
 @FXML
 private Button btConcordantie;
 @FXML
 private Button btClear;
 @FXML
 private TextArea taOutput;

 @Override
 public void initialize(URL location, ResourceBundle resources) {
  taInput.setText(DEFAULT_TEXT);
 }

 @FXML
 private void aantalAction(ActionEvent event) {
  addOutput("Totaal aantal woorden: " + countTotalWords(DEFAULT_TEXT));
  addOutput("Totaal aantal unieke woorden in de tekst: " + getDistinctWords().size());
  addOutput("");
 }

 @FXML
 private void sorteerAction(ActionEvent event) {
     addOutput("Aflopend gesorteerde woordenlijst: " + getSortedWords());
     addOutput("");
 }

 @FXML
 private void frequentieAction(ActionEvent event) {
     addOutput("Aantal dezelfde woorden: ");
     for (Map.Entry<String, Integer> entry : getDuplicateWordsCount().entrySet()) {
         addOutput("Woord : " + entry.getKey() + " Aantal : " + entry.getValue());
     }
     addOutput("");
 }

 @FXML
 private void concordatieAction(ActionEvent event) {
  throw new UnsupportedOperationException("Not supported yet.");
 }

 @FXML
 private void clearAction(ActionEvent event) {
     taOutput.clear();
 }

 private int countTotalWords(String stringToCount) {
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

 private ArrayList<String> getDistinctWords(){
  ArrayList<String> distinctWords = new ArrayList<>();

  for (String s : getSeperatedWords()) {
    if(!distinctWords.contains(s)){ //add the word if it isn't added already
       distinctWords.add(s);
    }
  }

  return distinctWords; //return the list you made of distinct words
 }

 private ArrayList<String> getSortedWords(){
     ArrayList<String> sortedWords = getDistinctWords();
     //sortedWords.sort(String::compareToIgnoreCase);
     Collections.sort(sortedWords, Collections.reverseOrder());
     return sortedWords;
 }

 private TreeMap<String, Integer> getDuplicateWordsCount(){
     Map<String, Integer> map = new HashMap<>();

     for (String temp : getSeperatedWords()) {
         Integer count = map.get(temp);
         map.put(temp, (count == null) ? 1 : count + 1);
     }

     // sort the map order
     return new TreeMap<>(map);
 }

 private TreeMap<String, Integer> getConcordanceWords(){
     
 }

 private void addOutput(String text) {
    taOutput.setText(taOutput.getText() + text + "\n");
 }

}