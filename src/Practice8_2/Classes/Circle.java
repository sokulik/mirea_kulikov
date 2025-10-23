package Practice8_2.Classes;

import java.awt.*;
import java.math.*;

public class Circle extends Shape{
    private double radius;

    public Circle() {
        super();
        this.radius = 0.0;
    }
    public Circle(double radius){
        super();
        this.radius = radius;
    }
    public Circle(double radius, Color color, boolean filled){
        super(color, filled);
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
        return Math.PI*radius*radius;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int diameter = (int) (2 * radius);
        if (filled) {
            g.fillOval(x, y, diameter, diameter);
        } else {
            g.drawOval(x, y, diameter, diameter);
        }
    }

    @Override
    public String toString() {
        return "Circle[" +
                "radius=" + radius +
                "]";
    }
}