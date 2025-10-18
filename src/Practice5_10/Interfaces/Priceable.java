package Practice5_10.Interfaces;

public interface Priceable {
    double getPrice();
    default String getPriceFormatted() {
        return String.format("%.2f руб.", getPrice());
    }
}