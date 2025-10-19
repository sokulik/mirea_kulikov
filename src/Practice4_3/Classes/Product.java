package Practice4_3.Classes;

import Practice4_3.Enums.*;
import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private double price;
    private String category;
    private Size size;
    private String info;


    public Product(String name, Size size, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.size = size;
    }

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(String name, String info, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Size getSize() {
        return size;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        if (info != null) {
            return name + " - " + info + " | " + price + " руб. | категория - " + category;
        }
        else if (size != null) {
            return name + " - " + size + " | " + price + " руб. | категория - " + category;
        }
        else {
            return name +" | " + price + " руб. | категория - " + category;
        }
    }



}
