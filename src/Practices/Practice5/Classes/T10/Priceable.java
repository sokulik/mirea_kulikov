package Practices.Practice5.Classes.T10;

public interface Priceable {
    double getPrice();
    default String getPriceFormatted() {
        return String.format("%.2f руб.", getPrice());
    }
}