package Practice7_2;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Task2 {
    public static void task2() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        while (true) {
            Date now = new Date();

            try {
                System.out.println("\n═══════════════════════════════════════");
                System.out.println("Введите дату для сравнения в формате ДД.ММ.ГГГГ ЧЧ:ММ:СС");
                System.out.println("(Для выхода введите '0')");
                System.out.print(">>>>>> ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("0")) {
                    System.out.println("Программа завершена. До свидания!");
                    break;
                }

                if (userInput.trim().isEmpty()) {
                    System.out.println("Ошибка: ввод не может быть пустым!");
                    continue;
                }

                Date userDate = sdf.parse(userInput);

                System.out.println("Введенная дата: " + sdf.format(userDate) +
                        "\nТекущая дата: " + sdf.format(now));

                int comparison = userDate.compareTo(now);

                if (comparison == 0) {
                    System.out.println("Даты совпадают!");
                } else if (comparison < 0) {
                    System.out.println("Введеная дата раньше нынешней!");

                    long diff = now.getTime() - userDate.getTime();
                    long diffSeconds = diff / 1000;
                    long diffMinutes = diff / 60 / 1000;
                    long diffHours = diff / 60 / 60 / 1000;
                    long diffDays = diff / 24 / 60 / 60 / 1000;

                    System.out.println("Разница: " + diffDays + " дней, " + (diffHours % 24) + " часов, " + (diffMinutes % 60)
                            + " минут, " + (diffSeconds % 60) + " секунд");

                } else {
                    System.out.println("Введенная дата позже нынешней");

                    long diff = userDate.getTime() - now.getTime();
                    long diffSeconds = diff / 1000;
                    long diffMinutes = diff / 60 / 1000;
                    long diffHours = diff / 60 / 60 / 1000;
                    long diffDays = diff / 24 / 60 / 60 / 1000;

                    System.out.println("Разница: " + diffDays + " дней, " + (diffHours % 24) + " часов, " + (diffMinutes % 60)
                            + " минут, " + (diffSeconds % 60) + " секунд");

                }

            } catch (ParseException e) {
                System.out.println("Ошибка, неверный ввод. \nВведите дату для сравнения в формате ДД.ММ.ГГГГ ЧЧ:ММ:СС ");
            }
        }
    }
}

