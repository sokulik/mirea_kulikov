package Practice5_12;

import Practice5_12.Classes.*;
import java.util.Scanner;

public class Task12 {
    public static void task12() {
        System.out.println("=== Демонстрационный класс Задание 12 ===");

        UndoableStringBuilder usb = new UndoableStringBuilder();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n1. Выполняем автоматические операции:");
        usb.append("Hello");
        System.out.println("append('Hello'): " + usb);

        usb.append(" World");
        System.out.println("append(' World'): " + usb);

        usb.insert(5, ",");
        System.out.println("insert(5, ','): " + usb);

        usb.replace(7, 12, "Java");
        System.out.println("replace(7, 12, 'Java'): " + usb);

        System.out.println("\n2. История команд:");
        for (String entry : usb.getHistory()) {
            System.out.println(entry);
        }

        // ИНТЕРАКТИВНАЯ ЧАСТЬ С ВВОДОМ ОТ ПОЛЬЗОВАТЕЛЯ
        System.out.println("\n=== ИНТЕРАКТИВНЫЙ РЕЖИМ ===");

        boolean running = true;
        while (running) {
            System.out.println("\nТекущая строка: " + usb);
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить текст (append)");
            System.out.println("2. Вставить текст (insert)");
            System.out.println("3. Заменить текст (replace)");
            System.out.println("4. Удалить текст (delete)");
            System.out.println("5. Перевернуть строку (reverse)");
            System.out.println("6. Отменить последнюю операцию (undo)");
            System.out.println("7. Повторить отмененную операцию (redo)");
            System.out.println("8. Показать историю операций");
            System.out.println("9. Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите текст для добавления: ");
                    String appendText = scanner.nextLine();
                    usb.append(appendText);
                    System.out.println("Результат: " + usb);
                    break;

                case 2:
                    System.out.print("Введите позицию для вставки: ");
                    int insertPosition = scanner.nextInt();
                    scanner.nextLine(); // очистка буфера
                    System.out.print("Введите текст для вставки: ");
                    String insertText = scanner.nextLine();
                    usb.insert(insertPosition, insertText);
                    System.out.println("Результат: " + usb);
                    break;

                case 3:
                    System.out.print("Введите начальную позицию замены: ");
                    int replaceStart = scanner.nextInt();
                    System.out.print("Введите конечную позицию замены: ");
                    int replaceEnd = scanner.nextInt();
                    scanner.nextLine(); // очистка буфера
                    System.out.print("Введите новый текст: ");
                    String replaceText = scanner.nextLine();
                    usb.replace(replaceStart, replaceEnd, replaceText);
                    System.out.println("Результат: " + usb);
                    break;

                case 4:
                    System.out.print("Введите начальную позицию удаления: ");
                    int deleteStart = scanner.nextInt();
                    System.out.print("Введите конечную позицию удаления: ");
                    int deleteEnd = scanner.nextInt();
                    usb.delete(deleteStart, deleteEnd);
                    System.out.println("Результат: " + usb);
                    break;

                case 5:
                    usb.reverse();
                    System.out.println("Результат: " + usb);
                    break;

                case 6:
                    if (usb.undo()) {
                        System.out.println("Операция отменена. Результат: " + usb);
                    } else {
                        System.out.println("Нет операций для отмены.");
                    }
                    break;

                case 7:
                    if (usb.redo()) {
                        System.out.println("Операция повторена. Результат: " + usb);
                    } else {
                        System.out.println("Нет операций для повтора.");
                    }
                    break;

                case 8:
                    System.out.println("История операций:");
                    for (String entry : usb.getHistory()) {
                        System.out.println(entry);
                    }
                    break;

                case 9:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }


        System.out.println("\n=== ФИНАЛЬНЫЙ РЕЗУЛЬТАТ ===");
        System.out.println("Строка: " + usb);
        System.out.println("Длина: " + usb.length());
        System.out.println("Можно отменить: " + usb.canUndo());
        System.out.println("Можно повторить: " + usb.canRedo());
    }
}