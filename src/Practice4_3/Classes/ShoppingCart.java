package Practice4_3.Classes;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }
    public void clear(){
        items.clear();
    }

    @Override
    public String toString(){
        if(items.isEmpty()){
            return "Корзина пуста";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Содержимое корзины:\n");
        for (int j = 0; j < items.size(); j++){
            sb.append((j+1)+". "+items.get(j).toString()+"\n");
        }
        sb.append("общая сумма: "+getTotalPrice()+" руб.");
        return sb.toString();
    }

}
