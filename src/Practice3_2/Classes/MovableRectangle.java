package Practice3_2.Classes;

import Practice3_2.Interfaces.*;

public class MovableRectangle implements Movable{
    private MovablePoint topLeft;
    private MovablePoint bottomRight;

    public MovableRectangle(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed){
        this.topLeft = new MovablePoint(x1, y1, xSpeed, ySpeed);
        this.bottomRight = new MovablePoint(x2, y2, xSpeed, ySpeed);
    }

    private boolean SameSpeed(){
        return topLeft.getxSpeed() == bottomRight.getxSpeed() &&
                topLeft.getySpeed() == bottomRight.getySpeed() &&
                topLeft.getySpeed() == bottomRight.xSpeed;
    }

    @Override
    public void moveUp() {
        if (SameSpeed()) {
            topLeft.moveUp();
            bottomRight.moveUp();
        } else {
            System.out.println("Скорости точек не совпадают!");
        }
    }

    @Override
    public void moveDown() {
        if (SameSpeed()) {
            topLeft.moveDown();
            bottomRight.moveDown();
        } else {
            System.out.println("Скорости точек не совпадают!");
        }
    }

    @Override
    public void moveRight() {
        if (SameSpeed()) {
            topLeft.moveRight();
            bottomRight.moveRight();
        } else {
            System.out.println("Скорости точек не совпадают!");
        }
    }

    @Override
    public void moveLeft() {
        if (SameSpeed()) {
            topLeft.moveLeft();
            bottomRight.moveLeft();
        } else {
            System.out.println("Скорости точек не совпадают!");
        }
    }

    public MovablePoint getTopLeft(){
        return topLeft;
    }


    public MovablePoint getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return "Прямоугольник:" +
                "\nТочка №1 - " + topLeft.toString() +
                " | Точка №2(противоположная) - " + bottomRight.toString();
    }
}
