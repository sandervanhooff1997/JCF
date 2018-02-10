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
import woordenapplicatie.IWoordenManager;
import woordenapplicatie.WoordenManager;

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

 private IWoordenManager manager;

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
     manager = new WoordenManager(DEFAULT_TEXT);
     taInput.setText(DEFAULT_TEXT);
 }

 @FXML
 private void aantalAction(ActionEvent event) {
  addOutput("Totaal aantal woorden: " + manager.countTotalWords(DEFAULT_TEXT));
  addOutput("Totaal aantal unieke woorden in de tekst: " + manager.getDistinctWords().size());
  addOutput("");
 }

 @FXML
 private void sorteerAction(ActionEvent event) {
     addOutput("Aflopend gesorteerde woordenlijst: " + manager.getSortedWords());
     addOutput("");
 }

 @FXML
 private void frequentieAction(ActionEvent event) {
     addOutput("Aantal dezelfde woorden: ");
     for (Map.Entry<String, Integer> entry : manager.getDuplicateWordsCount().entrySet()) {
         addOutput("Woord : " + entry.getKey() + " Aantal : " + entry.getValue());
     }
     addOutput("");
 }

 @FXML
 private void concordatieAction(ActionEvent event) {
     for (Map.Entry<String, LinkedList<Integer>> stringInt : manager.getConcordanceWords().entrySet()) {  // O(n)
         addOutput(stringInt.getKey()+" : "+ stringInt.getValue());
     }

 }

 @FXML
 private void clearAction(ActionEvent event) {
     taOutput.clear();
 }

 private void addOutput(String text) {
    taOutput.setText(taOutput.getText() + text + "\n");
 }

}