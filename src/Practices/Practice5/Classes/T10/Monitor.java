package Practices.Practice5.Classes.T10;

public class Monitor implements Priceable {
    private M_Brand brand;
    private M_name model;

    public Monitor(M_Brand brand, M_name model) {
        this.brand = brand;
        this.model = model;
    }

    public M_name getModel() { return model; }
    public M_Brand getBrand() { return brand; }

    @Override
    public double getPrice() {
        return model.getPrice();
    }

    @Override
    public String toString() {
        return "Монитор " + brand + " " + model.getName() +
                " (" + model.getSize() + ", " + model.getRefreshRate() +
                ") - " + getPriceFormatted();
    }
}