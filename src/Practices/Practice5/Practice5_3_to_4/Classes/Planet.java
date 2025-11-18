package Practice5_3_to_4.Classes;
import Practice5_3_to_4.Interfaces.*;

public class Planet implements Nameable{
    private String name;

    public Planet(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "Планета: " + name;
    }
}
