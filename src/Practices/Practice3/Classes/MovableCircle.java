package Practices.Practice3.Classes;

public class MovableCircle implements Movable{
    private int radius;
    private MovablePoint center;

    public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius){
        this.center = new MovablePoint(x, y, xSpeed, ySpeed);
        this.radius = radius;
    }

    @Override
    public void moveUp() {
        center.moveUp();
    }

    @Override
    public void moveDown() {
        center.moveDown();
    }

    @Override
    public void moveRight() {
        center.moveRight();
    }

    @Override
    public void moveLeft() {
        center.moveLeft();
    }

    public int getRadius() {
        return radius;
    }

    public MovablePoint getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Круг - " +
                "Радиус{" + radius +
                "} | Центр - " + center.toString() +
                "\nГраницы окружности: Левая - {"+getLeftBound()+"} | Правая - {"+ getRightBound()
                +"} | Верхняя - {"+getTopBound()+"} | Нижняя - {"+getBottomBound()+"}.";

    }
    public int getLeftBound(){
        return center.getX() - radius;
    }
    public int getRightBound(){
        return center.getX() + radius;
    }
    public int getTopBound(){
        return center.getY() + radius;
    }
    public int getBottomBound(){
        return center.getY() - radius;
    }
}
