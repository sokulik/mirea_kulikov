package Practices.Practice5.Classes.T10;

import java.io.Serializable;

public abstract class Product implements Priceable, Displayable, Serializable {
    protected String name;
    protected double price;
    protected String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String getPriceFormatted() {
        return String.format("%.2f руб.", price);
    }

    @Override
    public String getShortInfo() {
        return name + " - " + getPriceFormatted();
    }

    @Override
    public abstract String display();

    @Override
    public String toString() {
        return display();
    }
}