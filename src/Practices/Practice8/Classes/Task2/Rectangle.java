package Practices.Practice8.Classes.Task2;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public Rectangle(int width, int height, Color color, boolean filled, int x, int y) {
        super(color, filled, x, y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void draw(Graphics g) {
        if (filled) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        } else {
            g.setColor(color);
            g.drawRect(x, y, width, height);
        }
    }

    @Override
    public String toString() {
        return "Rectangle[width=" + width + ", height=" + height + ", x=" + x +
                ", y=" + y + ", color=" + color + ", filled=" + filled + "]";
    }
}