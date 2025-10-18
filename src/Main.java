import Practice6_4.Task4;
import Practice6_5_and_6.Task5_6;
import Practice6_7_and_8.Task7_8;

import java.util.*;

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
                        +"\n1 - Задание 4\n"
                        +"2 - Задания 5-6\n"
                        +"3 - Задания 7-8\n"
                        +"0 - Выход\n"+"=".repeat(80)
                        +"\nВведите номер >>>");

                choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        clearConsole();
                        Task4.task4();
                        waitForEnter();
                        clearConsole();
                        continue;
                    case 2:
                        clearConsole();
                        Task5_6.task5_6();
                        waitForEnter();
                        clearConsole();
                        continue;
                    case 3:
                        clearConsole();
                        Task7_8.task7_8();
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

