package Practices.Practice5.Classes.T34;

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
