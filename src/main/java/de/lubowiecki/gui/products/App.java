package de.lubowiecki.gui.products;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        // Inhalt der Szene wird auf Basis der FXML-Datei zusammengestellt
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        /*
        VBox container = new VBox();
        Button b = new Button("Beschreibung...");
        // Bei Interaktion mit Bedienelementen werden Events geworfen
        // EventHandler: void handle(T var1);
        b.setOnAction(e -> {
            // Hier kommt die Behandlung des Events rein
            System.out.println("Hallo Button");
        });

        container.getChildren().add(b);
        Scene scene = new Scene(container); // Fügt Elemente zu einer Szene hinzu
        stage.setTitle("Hello!");
        stage.setScene(scene); // Stellt die Szene auf die Bühne
        stage.show(); // Bühne wird sichtbar gemacht
        */
    }

    public static void main(String[] args) {
        launch();
    }
}