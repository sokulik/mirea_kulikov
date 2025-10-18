package Practice5_6_to_9.Classes;
import Practice5_6_to_9.Interfaces.Printable;

public class Magazine implements Printable{
    private String name;
    private String type;
    private double price;

    public Magazine(String name, String type, double price){
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public void print(){
        System.out.println("Журнал: "+name+" | Жанр: "+type+" | Цена: "+price);
    }
}
