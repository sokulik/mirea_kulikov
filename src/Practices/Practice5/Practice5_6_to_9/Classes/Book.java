package Practice5_6_to_9.Classes;
import Practice5_6_to_9.Interfaces.Printable;
public class Book implements Printable{
    private String name;
    private String author;
    private String type;
    private double price;

    public Book(String name, String author, String type, double price){
        this.name = name;
        this.author = author;
        this.type = type;
        this.price = price;
    }

    @Override
    public void print() {
        System.out.println("Книга: "+name+" | Автор: "+author+
                " | Жанр: "+type+" | Цена: "+price);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}