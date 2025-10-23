package Practice8_2.Classes;

import java.awt.*;
import java.util.Random;

public class ShapeFactory {
    private static final Random random = new Random();

    public static Shape createRandomShape(int maxX, int maxY){
        int shapeType = random.nextInt(4);
        Color randomColor = new Color(random.nextInt(256),
                random.nextInt(256), random.nextInt(256), random.nextInt(256));
        boolean filled = random.nextBoolean();
        int x = random.nextInt(maxX - 100);
        int y = random.nextInt(maxY - 100);

        switch (shapeType) {
            case 0: //Круг
                int radius = random.nextInt(50) + 10;
                return new Circle(radius, randomColor, filled, x, y);
            case 1: //Прямоугольник
                int width = random.nextInt(100) + 20;
                int length = random.nextInt(100) + 20;
                return new Rectangle(width, length, randomColor, filled, x, y);
            case 2: //Квадрат
                int side = random.nextInt(80) + 20;
                return new Square(side, randomColor, filled, x, y);
            case 3: //Треугольник
                int base = random.nextInt(80) + 20;
                int height = random.nextInt(80) + 20;
                return new Triangle(base, height, randomColor, filled, x, y);
        }
        return null;
    }
}
