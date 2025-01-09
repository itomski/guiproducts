package de.lubowiecki.gui.products;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Standard Controller für die GUI
 */
// Initializable wird ausgeführt sobald die Oberfläche gestartet wird
public class MainController implements Initializable {

    // Diese Annotation macht die Eigenschaft/Methode für die GUI sichtbar
    @FXML
    private TableView<Product> productTbl;

    private boolean old;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField count;

    @FXML
    private TextField price;

    @FXML
    private Button editBtn;

    // Hier werden die Daten verwaltet
    private List<Product> products;

    private static final String SER_FILE = System.getProperty("user.home") + "/data.ser";

    @FXML
    protected void save() {
        try {
            if(old) {
                updateProduct();
                editBtn.setStyle(null);
                old = false;
            }
            else {
                Product product = create();
                products.add(product);
            }
            clearFields();
            saveToFile();
            showProducts();
        }
        catch(Exception ex) {
            // TODO: Ausgabe in die GUI
            ex.printStackTrace();
        }
    }

    @FXML
    protected void delete() {
        // SelectionModel enthält die Informationen über Auswahl der Zeilen
        // getSelectedItem liefert das ausgewählte Produkt
        Product selected = productTbl.getSelectionModel().getSelectedItem();
        if(selected != null) {
            // Entfernt ausgewähltes Objekt aus der ArrayList
            products.remove(selected);
            saveToFile(); // Gespeicherten Daten aktuallisieren
            // Anzeige der Tabelle wird erneuert
            showProducts();
        }
    }

    @FXML
    protected void edit() {
        Product selected = productTbl.getSelectionModel().getSelectedItem();
        if(selected != null) {
            // Formular mit Daten befüllen
            name.setText(selected.getName());
            description.setText(selected.getDescription());
            count.setText(selected.getCount() + ""); // Muss als Text eingefügt werden
            price.setText(selected.getPrice() + ""); // Muss als Text eingefügt werden
            old = true;
            editBtn.setStyle("-fx-background-color: #00ff00");
        }
    }

    /**
     * Erzeugt ein Produkt basierend auf der Eingabe des Benutzers
     * @return
     * @throws Exception
     */
    private Product create() throws Exception {
        Product product = new Product();
        // TODO: Validierung
        // getText liefert den Inhalt des Textfeledes
        product.setName(name.getText());
        product.setDescription(description.getText());
        product.setCount(Integer.parseInt(count.getText()));
        product.setPrice(Double.parseDouble(price.getText()));
        return product;
    }

    public void updateProduct() {
        Product product = productTbl.getSelectionModel().getSelectedItem();
        product.setName(name.getText());
        product.setDescription(description.getText());
        product.setCount(Integer.parseInt(count.getText()));
        product.setPrice(Double.parseDouble(price.getText()));
        // TODO: Daten in der TableView ändern
    }

    private void clearFields() {
        // clear: leert das Textfeld
        name.clear();
        description.clear();
        count.clear();
        price.clear();
    }

    private void showProducts() {
        // setItems: gibt die products ArrayList in der Tabelle aus
        // observableList: Tabell verlangt eine ObservableList, daher muss die ArrayList umgewandelt werden
        productTbl.setItems(FXCollections.observableList(products));
        productTbl.refresh();
    }

    private void saveToFile() {
        // ObjectOutputStream: Speichert Objekte als Text
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SER_FILE))) {
            // Schreibt die Objekte (Liste von Produkten) in die Datei
            out.writeObject(products);
        }
        catch (IOException ex) {
            // TODO: Ausgabe in die GUI
            ex.printStackTrace();
        }
    }

    private void readFromFile() {
        // ObjectInputStream: Liest Text als Objekte ein
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(SER_FILE))) {
            // Liest die Daten aus der Datei ein und wandelt sie in eine Liste von Produkten um
            products = (List<Product>) in.readObject();
        }
        catch (Exception ex) {
            // TODO: Ausgabe in die GUI
            ex.printStackTrace();
            products = new ArrayList<>();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Wird beim Starten der Oberfläche ausgeführt
        readFromFile(); // Alt-Daten werden aus einer Datei eingelesen
        showProducts(); // Alt-Daten werden in der Tabelle angezeigt

        // Reaktion auf Interaktion kann über die FXML gesteuert werden
        // oder über die Zuweisung von EventHandlern an passende Bedienelemente

        // e ist ein Event-Objekt
        // EventHandeler beschreib, was bei einem bestimmten Event passieren soll
        // Hier: Entf-Taste wird betätigt
        productTbl.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.BACK_SPACE) {
                delete();
            }
        });
    }
}