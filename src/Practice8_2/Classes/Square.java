package Practice8_2.Classes;

import java.awt.Color;

public class Square extends Rectangle{
    protected double side;

    public Square(){
        super();
    }

    public Square(double side){
        super(side, side);
    }

    public Square(double side, Color color, boolean filled){
        super(side, side, color, filled);
    }

    public Square(double side, Color color, boolean filled, int x, int y){
        super(side, side, color, filled, x, y);
    }

    public double getSide() {
        return getWidth();
    }

    public void setSide(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public void setWidth(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public void setLength(double side) {
        super.setLength(side);
        super.setWidth(side);
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + getSide() +
                '}';
    }
}
