module de.lubowiecki.gui.products {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.lubowiecki.gui.products to javafx.fxml;
    exports de.lubowiecki.gui.products;
}