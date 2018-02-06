package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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
 private TextArea taOutput;

 @Override
 public void initialize(URL location, ResourceBundle resources) {
  taInput.setText(DEFAULT_TEXT);
 }

 @FXML
 private void aantalAction(ActionEvent event) {
  System.out.println("Totaal aantal woorden: " + countTotalWords(DEFAULT_TEXT));
  System.out.println("Totaal aantal unieke woorden in de tekst: " + getDistinctWords());
 }

 @FXML
 private void sorteerAction(ActionEvent event) {
  throw new UnsupportedOperationException("Not supported yet.");
 }

 @FXML
 private void frequentieAction(ActionEvent event) {
  throw new UnsupportedOperationException("Not supported yet.");
 }

 @FXML
 private void concordatieAction(ActionEvent event) {
  throw new UnsupportedOperationException("Not supported yet.");
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

 public int getDistinctWords(){

  Scanner scan = new Scanner(DEFAULT_TEXT);
  ArrayList<String> listOfWords = new ArrayList<String>();

  String word = scan.next(); //scanner automatically uses " " as a delimeter

  String[] words = DEFAULT_TEXT.split("\\s*(=>|,|\\s)\\s*");

  for (String s : words) {
   if(!listOfWords.contains(s)){ //add the word if it isn't added already
    listOfWords.add(s);
   }
  }

  return listOfWords.size(); //return the list you made of distinct words
 }




}