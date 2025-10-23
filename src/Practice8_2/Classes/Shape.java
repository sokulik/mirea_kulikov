package Practice8_2.Classes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
    protected Color color;
    protected boolean filled;
    protected int x;
    protected int y;

    public Shape() {
        this.color = Color.BLACK;
        this.filled = false;
        this.x = 0;
        this.y = 0;
    }

        public Shape(Color color, boolean filled) {
        this.color = color;
        this.filled = filled;
        this.x = 0;
        this.y = 0;
    }

    public Shape(Color color, boolean filled, int x, int y) {
        this.color = color;
        this.filled = filled;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract void draw(Graphics g);

}