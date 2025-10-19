package Practice3_2;

import Practice3_2.Classes.*;
import Practice3_2.Interfaces.*;

public class Task2 {
    public static void task2() {
        System.out.println("\n=== Тестировоание Movable ===");
        MovablePoint a = new MovablePoint(0, 0, 2, 2);
        System.out.println("\nСТАРТ: "+a);
        a.moveUp();
        a.moveLeft();
        System.out.println("ФИНИШ(после moveUp и moveLeft): "+a);

        System.out.println("\n\n=== Тестировоание MovableCircle ===");
        MovableCircle b = new MovableCircle(0, 0, 10, 10, 5);
        System.out.println("\nСТАРТ: "+b);
        b.moveDown();
        b.moveRight();
        System.out.println("ФИНИШ(после moveDown и moveRight): "+b);

        System.out.println("\n\n=== Тестирование MovableRectangle ===");
        MovableRectangle r = new MovableRectangle(0, 0, 4, 4, 2, 2);
        System.out.println("СТАРТ "+r);
        r.moveLeft();
        r.moveUp();
        System.out.println("ФИНИШ(после moveLeft и moveUp): "+r);

        System.out.println("\n\n=== Проверка на разные скорости ===\n===(ставим xSpeed = 2 и ySpeed = 3) ===");
        MovableRectangle r1 = new MovableRectangle(0, 0, 1, 1, 2, 3);
        r1.moveUp();
    }
}

