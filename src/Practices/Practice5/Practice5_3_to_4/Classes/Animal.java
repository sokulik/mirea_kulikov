package Practice5_3_to_4.Classes;
import Practice5_3_to_4.Interfaces.*;

public class Animal implements Nameable {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "Животное: " + name;
    }
}