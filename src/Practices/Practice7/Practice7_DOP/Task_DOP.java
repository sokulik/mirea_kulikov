package Practice7_DOP;

import java.util.*;


public class Task_DOP {
    public static void clearConsoleD() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void waitForEnterD() {
        System.out.println("\nНажмите Enter для возвращения к выбору задания...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    //Задание 1
    public static void task1d(){
        long[] timeValues = {
                10000L,
                100000L,
                1000000L,
                10000000L,
                100000000L,
                1000000000L,
                10000000000L,
                100000000000L,
                1000000000000L,
                10000000000000L,
                100000000000000L,
                1000000000000000L
        };
        System.out.println("Время с 1 января 1970 года | Дата и время"+"\n"+"-".repeat(80));
        for(long time : timeValues){
            Date date = new Date(time);
            System.out.printf("%,30d мс | %s\n", time, date.toString());
        }
    }

    //Задание 2
    public static void task2d() {
        Random random = new Random(1);

        System.out.println("Первые 50 случайных чисео от 0 до 100\n" + "-".repeat(80));

        for (int i = 1; i <= 50; i++) {
            int number = random.nextInt(100);
            System.out.printf("%3d", number);

            if (i % 10 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        Random sameRandom = new Random(1);
        System.out.println("-".repeat(80)+"\nДемонстрация работы воспроизводимости одного и того же сида(первые 5 чисел)" +
                "\n" + "-".repeat(80));
        for (int i = 1; i <= 5; i++) {
            int number = sameRandom.nextInt(100);
            System.out.printf("%3d", number);
        }
    }

    //Задание 3
    public static void task3d(){
        GregorianCalendar currentDate = new GregorianCalendar();

        int currentYear = currentDate.get(GregorianCalendar.YEAR);
        int currentMonth = currentDate.get(GregorianCalendar.MONTH)+1;
        int currentDay = currentDate.get(GregorianCalendar.DAY_OF_MONTH);

        System.out.println("1. Текущая дата\n"+"-".repeat(80)+"\nГод: "+currentYear
                +"\nМесяц: "+currentMonth
                +"\nДень: "+currentDay
                +"\nОбщая дата: "+currentDay+"."+currentMonth+"."+currentYear);

        GregorianCalendar staticDate = new GregorianCalendar();
        staticDate.setTimeInMillis(1234567898765L);

        int staticYear = staticDate.get(GregorianCalendar.YEAR);
        int staticMonth = staticDate.get(GregorianCalendar.MONTH)+1;
        int staticDay = staticDate.get(GregorianCalendar.DAY_OF_MONTH);

        System.out.println("\n"+"-".repeat(80)+"\n1. Кастомная дата\n"+"-".repeat(80)+"\nГод: "+staticYear

                +"\nМесяц: "+staticMonth
                +"\nДень: "+staticDay
                +"\nОбщая дата: "+staticDay+"."+staticMonth+"."+staticYear);

    }


    public static void task_dop() {
        Scanner scannerD = new Scanner(System.in);
        int choice;
        do {
            System.out.println("=".repeat(80)
                    +"\nМеню выбора для дополнительных заданий по практической 7\n"
                    + "=".repeat(80)
                    + "\n1 - Задание 1\n"
                    + "2 - Задание 2\n"
                    + "3 - Задание 3\n"
                    + "0 - Выход\n"+"=".repeat(80)
                    + "\nВведите номер >>>");

            choice = scannerD.nextInt();

            switch (choice) {
                case 1:
                    clearConsoleD();
                    task1d();
                    waitForEnterD();
                    clearConsoleD();
                    continue;
                case 2:
                    clearConsoleD();
                    task2d();
                    waitForEnterD();
                    clearConsoleD();
                    continue;
                case 3:
                    clearConsoleD();
                    task3d();
                    waitForEnterD();
                    clearConsoleD();
                    continue;
                case 0:
                    System.out.println("Выход..........");
                    break;
                default:
                    System.out.println("Вы ввели недопустимое значение, попробуйте еще раз!");

            }

        }while (choice != 0);
    }
    }