package Practice5_3_to_4.Classes;
import Practice5_3_to_4.Interfaces.*;

public class Product implements Priceable {
    private String title;
    private double price;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}