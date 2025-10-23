package Practice8_2.Classes;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends Shape{
    protected double width;
    protected double length;

    public Rectangle(){
        super();
        this.width = 0.0;
        this.length = 0.0;
    }

    public Rectangle(double width, double length){
        super();
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, Color color, boolean filled){
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, Color color, boolean filled, int x, int y) {
        super(color, filled, x, y);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        return width*length;
    }

    @Override
    public double getPerimeter() {
        return 2*(length+width);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillRect(x, y, (int) width, (int) length);
        } else {
            g.drawRect(x, y, (int) width, (int) length);
        }
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}
