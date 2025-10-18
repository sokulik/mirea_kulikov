import java.util.*;
import Practice5_3_to_4.*;
import Practice5_6_to_9.*;
import Practice5_10.*;
import Practice5_11.*;
import Practice5_12.*;
import Practice5_13.*;

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
                        +"\n1 - Задания 3,4\n"
                        +"2 - Задания 6,7,8,9\n"
                        +"3 - Задание 10\n"
                        +"4 - Задание 11\n"
                        +"5 - Задание 12\n"
                        +"6 - Задание 13\n"
                        +"0 - Выход\n"+"=".repeat(80)
                        +"\nВведите номер >>>");

                choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        clearConsole();
                        Task3_4.task3_4();
                        waitForEnter();
                        clearConsole();
                        continue;
                    case 2:
                        clearConsole();
                        Task6_9.task6_9();
                        waitForEnter();
                        clearConsole();
                        continue;
                    case 3:
                        clearConsole();
                        Task10.task10();
                        clearConsole();
                        continue;
                    case 4:
                        clearConsole();
                        Task11.task11();
                        waitForEnter();
                        clearConsole();
                        continue;
                    case 5:
                        clearConsole();
                        Task12.task12();
                        waitForEnter();
                        clearConsole();
                        continue;
                    case 6:
                        clearConsole();
                        Task13.task13();
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
