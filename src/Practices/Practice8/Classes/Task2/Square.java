package Practices.Practice8.Classes.Task2;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
    private int side;

    public Square(int side) {
        super();
        this.side = side;
    }

    public Square(int side, Color color, boolean filled, int x, int y) {
        super(color, filled, x, y);
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public void draw(Graphics g) {
        if (filled) {
            g.setColor(color);
            g.fillRect(x, y, side, side);
        } else {
            g.setColor(color);
            g.drawRect(x, y, side, side);
        }
    }

    @Override
    public String toString() {
        return "Square[side=" + side + ", x=" + x + ", y=" + y +
                ", color=" + color + ", filled=" + filled + "]";
    }
}