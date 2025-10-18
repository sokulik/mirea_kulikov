package Practice6_4;
import Practice6_4.Classes.MathFunc;
import Practice6_4.Interfaces.MathCalculable;

import java.util.Scanner;
public class Task4 {
    public static void task4() {
        Scanner scanner = new Scanner(System.in);

        MathCalculable mc1 = new MathFunc();
        MathFunc mathFunc = new MathFunc();

        // Тестирование
        System.out.println("PI = " + MathCalculable.PI);
        System.out.println("2^3 = " + mc1.Exponentiation(2, 3));

        System.out.println("=== МАТЕМАТИЧЕСКИЕ ВЫЧИСЛЕНИЯ ===");

        while (true) {
            System.out.println("\nВыберите операцию:");
            System.out.println("1 - Возведение в степень");
            System.out.println("2 - Модуль комплексного числа");
            System.out.println("3 - Длина окружности");
            System.out.println("4 - Площадь круга");
            System.out.println("0 - Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Выход из программы...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Введите основание: ");
                    double base = scanner.nextDouble();
                    System.out.print("Введите показатель степени: ");
                    double exponent = scanner.nextDouble();
                    System.out.printf("Результат: %.2f^%.2f = %.2f%n",
                            base, exponent, mathFunc.Exponentiation(base, exponent)); // Исправлено на mathFunc
                    break;

                case 2:
                    System.out.print("Введите действительную часть: ");
                    double real = scanner.nextDouble();
                    System.out.print("Введите мнимую часть: ");
                    double imaginary = scanner.nextDouble();
                    System.out.printf("Модуль комплексного числа (%.1f + %.1fi) = %.2f%n",
                            real, imaginary, mathFunc.Complex(real, imaginary)); // Исправлено на mathFunc
                    break;

                case 3:
                    System.out.print("Введите радиус окружности: ");
                    double radius1 = scanner.nextDouble();
                    System.out.printf("Длина окружности с радиусом %.1f = %.2f%n",
                            radius1, mathFunc.circleLenght(radius1)); // Исправлено на mathFunc
                    break;

                case 4:
                    System.out.print("Введите радиус круга: ");
                    double radius2 = scanner.nextDouble();
                    System.out.printf("Площадь круга с радиусом %.1f = %.2f%n",
                            radius2, mathFunc.circleArea(radius2)); // Исправлено на mathFunc
                    break;

                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }
}

