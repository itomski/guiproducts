package de.lubowiecki.gui.products;

import java.io.Serializable;

// Serializable ist ein Marker-Interface
// Es erlaubt Java die Daten zu serialisieren (als Text in Dateien zu schreiben)
public class Product implements Serializable {

    private String name;
    private String description;
    private int count;
    private double price;

    public Product() {
    }

    public Product(String name, String description, int count, double price) {
        this.name = name;
        this.description = description;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
