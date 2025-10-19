import java.util.*;
import Practice3_1.*;
import Practice3_2.*;

public class Main {
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void waitForEnter() {
        System.out.println("\nНажмите Enter для возвращения к выбору задания...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;



        do {
            System.out.println("=".repeat(80)+"\nМеню выбора заданий по практической 7\n"+"=".repeat(80)
                    +"\n1 - Задание 1\n"
                    +"2 - Задание 2\n"
                    +"0 - Выход\n"+"=".repeat(80)
                    +"\nВведите номер >>>");

            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    clearConsole();
                    Task1.task1();
                    waitForEnter();
                    clearConsole();
                    continue;
                case 2:
                    clearConsole();
                    Task2.task2();
                    waitForEnter();
                    clearConsole();
                    continue;
                case 0:
                    System.out.println("Выход..........");
                    break;
                default:
                    System.out.println("Вы ввели недопустимое значение, попробуйте еще раз!");

            }


        }while (choice != 0);
        scanner.close();
    }


}