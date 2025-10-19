package Practice3_1;

import Practice3_1.Classes.*;

public class Task1 {
    public static void task1() {
        Circle circle1 = new Circle();
        Circle circle2 = new Circle(5.0);
        Circle circle3 = new Circle(3.0, "blue", true);

        System.out.println("\n=== Тестирование Circle ===");
        System.out.println("Круг №1: "+circle1+" | Площадь: "+circle1.getArea()
                +" | Периметр: "+circle1.getPerimeter()
                +"\nЦвет: "+circle1.getColor()+" | Заполнение: "+circle1.isFilled());

        System.out.println("\nКруг №2: "+circle2+" | Площадь: "+circle2.getArea()
                +" | Периметр: "+circle2.getPerimeter()
                +"\nЦвет: "+circle2.getColor()+" | Заполнение: "+circle2.isFilled());

        System.out.println("\nКруг №3: "+circle3+" | Площадь: "+circle3.getArea()
                +" | Периметр: "+circle3.getPerimeter()
                +"\nЦвет: "+circle3.getColor()+" | Заполнение: "+circle3.isFilled());


        circle1.setRadius(2.5);
        circle1.setColor("Red&White");
        circle1.setFilled(false);
        System.out.println("\nКруг №1 после внесенных изменений: "+circle1+"\nПлощадь: "+circle1.getArea()
                +" | Периметр: "+circle1.getPerimeter()
                +"\nЦвет: "+circle1.getColor()+" | Заполнение: "+circle1.isFilled());

        Rectangle rectangle1 = new Rectangle();
        Rectangle rectangle2 = new Rectangle(10.0, 15.0);
        Rectangle rectangle3 = new Rectangle(10.3, 6.8, "Black", true);

        System.out.println("\n=== Тестирование Rectangle ===");
        System.out.println("Прямоугольник №1: "+rectangle1+" | Периметр: "+rectangle1.getPerimeter()
                +" | Площадь: "+rectangle1.getArea()
                + "\nЦвет: "+rectangle1.getColor()+" | Заполнение - "+rectangle1.isFilled());

        System.out.println("\nПрямоугольник №2: "+rectangle2+" | Периметр: "+rectangle2.getPerimeter()
                +" | Площадь: "+rectangle2.getArea()
                + "\nЦвет: "+rectangle2.getColor()+" | Заполнение - "+rectangle2.isFilled());

        System.out.println("\nПрямоугольник №3: "+rectangle3+" | Периметр: "+rectangle3.getPerimeter()
                +" | Площадь: "+rectangle3.getArea()
                + "\nЦвет: "+rectangle3.getColor()+" | Заполнение - "+rectangle3.isFilled());

        rectangle1.setLength(150.0);
        rectangle1.setWidth(600);
        rectangle1.setColor("White&Black");
        rectangle1.setFilled(true);
        System.out.println("\nПрямоугольник №1 после внесенных изменений: "+rectangle1
                +"\nПериметр: "+rectangle1.getPerimeter() +" | Площадь: "+rectangle1.getArea()
                + "\nЦвет: "+rectangle1.getColor()+" | Заполнение - "+rectangle1.isFilled());

        Square square1 = new Square();
        Square square2 = new Square(4.0);
        Square square3 = new Square(25.0, "Yellow", true);

        System.out.println("\n=== Тестирование Square ===");
        System.out.println("Квадрат №1: "+square1+" | Периметр: "+square1.getPerimeter()
                +" | Площадь: "+square1.getArea()
                +"\nЦвет: "+square1.getColor()+" | Заполнение: "+square1.isFilled());

        System.out.println("\nКвадрат №2: "+square2+" | Периметр: "+square2.getPerimeter()
                +" | Площадь: "+square2.getArea()
                +"\nЦвет: "+square2.getColor()+" | Заполнение: "+square2.isFilled());

        System.out.println("\nКвадрат №3: "+square3+" | Периметр: "+square3.getPerimeter()
                +" | Площадь: "+square3.getArea()
                +"\nЦвет: "+square3.getColor()+" | Заполнение: "+square3.isFilled());

        square1.setSide(225);
        square1.setColor("White&Blue&Red");
        square1.setFilled(true);

        System.out.println("\nКвадрат №1 после внесенных изменений: "+square1
                +"\nПериметр: "+square1.getPerimeter()+" | Площадь: "+square1.getArea()
                +"\nЦвет: "+square1.getColor()+" | Заполнение: "+square1.isFilled());

        System.out.println("\n=== Тестирование сеттеров Square ===");

        square1.setSide(95);
        System.out.println("\nКвадрат №1 после setSide(95): "+square1
                +"\nПериметр: "+square1.getPerimeter()+" | Площадь: "+square1.getArea()
                +"\nЦвет: "+square1.getColor()+" | Заполнение: "+square1.isFilled());

        square1.setLength(50);
        System.out.println("\nКвадрат №1 после setLength(50): "+square1
                +"\nПериметр: "+square1.getPerimeter()+" | Площадь: "+square1.getArea()
                +"\nЦвет: "+square1.getColor()+" | Заполнение: "+square1.isFilled());

        square1.setWidth(21);
        System.out.println("\nКвадрат №1 после setWidth(21): "+square1
                +"\nПериметр: "+square1.getPerimeter()+" | Площадь: "+square1.getArea()
                +"\nЦвет: "+square1.getColor()+" | Заполнение: "+square1.isFilled());

        System.out.println("\n=== Демонстрация полиморфизма ===");

        Shape[] s = new Shape[3];
        s[0] = new Circle(5.0, "Orange", false);
        s[1] = new Rectangle(2.0, 4.0, "Green", true);
        s[2] = new Square(6.0, "Pink", true);

        for (Shape shape : s){
            System.out.println("\nФигура: "+shape+"\nПериметр: "+shape.getPerimeter()
                    +" | Площадь: "+shape.getArea()
                    +"\nЦвет: "+shape.getColor()+" | Заполение: "+shape.isFilled());
        }
    }
}

