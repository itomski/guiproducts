package de.lubowiecki.gui.products;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    // Diese Annotation macht die Eigenschaft/Methode für die GUI sichtbar
    @FXML
    private Label ausgabe;

    @FXML
    private TextField eingabe;

    // private StringBuilder content = new StringBuilder();

    private List<Person> personen = new ArrayList<>();

    @FXML
    protected void zeigeEingabe() {
        String in = eingabe.getText(); // Text aus dem Textfeld einlesen
        // content.append(in).append("\n");
        // ausgabe.setText(content.toString()); // String in das Label ausgeben

        Person p = new Person(in); // Person erzeugen
        personen.add(p); // Neue Person der Liste hinzufügen
        showPersonen();

        //eingabe.setText(""); // Textfeld leeren
        eingabe.clear(); // Textfeld leeren
    }

    private void showPersonen() {
        StringBuilder sb = new StringBuilder();
        for(Person p : personen) {
            sb.append(p.getName()).append("\n");
        }
        ausgabe.setText(sb.toString()); // String von Namen in Label ausgeben
    }
}