package Practice5_10.Classes;

import Practice5_10.Interfaces.Priceable;

public class ComponentProduct extends Product {
    private Priceable component;

    public ComponentProduct(Priceable component, String name, String category) {
        super(name, component.getPrice(), category);
        this.component = component;
    }

    @Override
    public String display() {
        return component.toString();
    }
}