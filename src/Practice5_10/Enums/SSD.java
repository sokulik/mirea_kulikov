package Practice5_10.Enums;
import java.io.Serializable;

public enum SSD implements Serializable{
    GB_256(256, 2500),
    GB_512(512, 4000),
    GB_1024(1024, 7000);

private final int memory;
private final int price;

    SSD(int memory, int price) {

        this.memory = memory;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getMemory() {
        return memory;
    }

    public String getSSD() {

        return "Накопитель :"+memory+"Гб SSD - "+price+" руб.";
    }
}
