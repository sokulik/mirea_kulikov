package Practice5_10.Classes;

public class ComputerProduct extends Product {
    private Computer computer;

    public ComputerProduct(Computer computer) {
        super("Собранный компьютер", computer.getPrice(), "computers");
        this.computer = computer;
    }

    @Override
    public String display() {
        return computer.toString();
    }

    @Override
    public String getShortInfo() {
        return "💻 Компьютер - " + getPriceFormatted();
    }
}