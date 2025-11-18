package Practices.Practice8.Classes.Task2;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape {
    private int base;
    private int height;

    public Triangle(int base, int height) {
        super();
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
        // Для простоты считаем равнобедренный треугольник
        double side = Math.sqrt(Math.pow(base / 2.0, 2) + Math.pow(height, 2));
        return base + 2 * side;
    }

    @Override
    public void draw(Graphics g) {
        int[] xPoints = {x, x + base, x + base / 2};
        int[] yPoints = {y + height, y + height, y};

        if (filled) {
            g.setColor(color);
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.setColor(color);
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public String toString() {
        return "Triangle[base=" + base + ", height=" + height + ", x=" + x +
                ", y=" + y + ", color=" + color + ", filled=" + filled + "]";
    }
}