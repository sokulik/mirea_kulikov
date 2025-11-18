package Practices.Practice8.Classes.Task2;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
    private double radius;

    public Circle() {
        super();
        this.radius = 1.0;
    }

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public Circle(double radius, int x, int y) {
        super(Color.BLACK, false, x, y);
        this.radius = radius;
    }

    public Circle(double radius, Color color, boolean filled, int x, int y) {
        super(color, filled, x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw(Graphics g) {
        if (filled) {
            g.setColor(color);
            g.fillOval(x - (int)radius, y - (int)radius, (int)(2 * radius), (int)(2 * radius));
        } else {
            g.setColor(color);
            g.drawOval(x - (int)radius, y - (int)radius, (int)(2 * radius), (int)(2 * radius));
        }
    }

    @Override
    public String toString() {
        return "Circle[radius=" + radius + ", x=" + x + ", y=" + y +
                ", color=" + color + ", filled=" + filled + "]";
    }
}