package Practice8_2.Classes;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape {
    private int base;
    private int height;

    public Triangle() {
        super();
        this.base = 0;
        this.height = 0;
    }

    public Triangle(int base, int height) {
        super();
        this.base = base;
        this.height = height;
    }

    public Triangle(int base, int height, Color color, boolean filled) {
        super(color, filled);
        this.base = base;
        this.height = height;
    }

    public Triangle(int base, int height, Color color, boolean filled, int x, int y) {
        super(color, filled, x, y);
        this.base = base;
        this.height = height;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    @Override
    public double getPerimeter() {
        double side = Math.sqrt(Math.pow(base/2.0, 2) + Math.pow(height, 2));
        return base + 2 * side;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = {x, x + base/2, x + base};
        int[] yPoints = {y + height, y, y + height};
        if (filled) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "base=" + base +
                ", height=" + height +
                ", color=" + color +
                ", filled=" + filled +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
