package ru.mirea.kulikov.pr2;

public class TestBall {
    public static void main(String[] args) {
        Ball bl = new Ball(100, 100);
        System.out.println(bl);
        bl.move(30, 15);
        System.out.println(bl);
        bl.move(-180, 95);
        System.out.println(bl);
    }
}
