package Practices.Practice5.Classes.T34;

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