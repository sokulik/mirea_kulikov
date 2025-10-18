package Practice5_10.Classes;

import Practice5_10.Interfaces.Priceable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
        System.out.println("✅ Добавлено: " + product.getShortInfo());
    }

    public void removeItem(Product product) {
        if (items.remove(product)) {
            System.out.println("🗑️ Удалено: " + product.getShortInfo());
        }
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            Product removed = items.remove(index);
            System.out.println("🗑️ Удалено: " + removed.getShortInfo());
        }
    }

    public List<Product> getItems() {
        return new ArrayList<>(items); // Возвращаем копию для безопасности
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
        System.out.println("🛒 Корзина очищена");
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "🛒 Корзина пуста";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("🛒 Содержимое корзины:\n");

        for (int i = 0; i < items.size(); i++) {
            sb.append((i + 1) + ". " + items.get(i).getShortInfo() + "\n");
        }

        sb.append("\n💰 Общая сумма: " + String.format("%.2f", getTotalPrice()) + " руб.");
        sb.append("\n📦 Количество товаров: " + getItemCount());

        return sb.toString();
    }
}