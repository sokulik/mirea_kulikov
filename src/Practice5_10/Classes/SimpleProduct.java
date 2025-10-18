package Practice5_10.Classes;

import Practice5_10.Enums.Size;

public class SimpleProduct extends Product {
    private String brand;
    private String size;
    private String description;

    // Конструктор для обычных товаров
    public SimpleProduct(String name, double price, String category) {
        super(name, price, category);
    }

    // Конструктор для товаров с брендом
    public SimpleProduct(String name, String brand, double price, String category) {
        super(name, price, category);
        this.brand = brand;
    }

    // Конструктор для одежды с размером
    public SimpleProduct(String name, Size size, double price, String category) {
        super(name, price, category);
        this.size = size.toString();
    }

    // Конструктор для товаров с описанием
    public SimpleProduct(String name, double price, String description, String category) {
        super(name, price, category);
        this.description = description;
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("📦 ").append(name);

        if (brand != null) {
            sb.append(" (").append(brand).append(")");
        }
        if (size != null) {
            sb.append(" [Размер: ").append(size).append("]");
        }
        if (description != null) {
            sb.append(" - ").append(description);
        }

        sb.append(" - ").append(getPriceFormatted());
        return sb.toString();
    }

    public String getBrand() { return brand; }
    public String getSize() { return size; }
    public String getDescription() { return description; }
}