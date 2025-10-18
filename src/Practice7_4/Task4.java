package Practice7_4;
import java.util.*;
import java.text.SimpleDateFormat;

public class Task4 {

    public static void task4() {
        Scanner scanner = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM yyyy 'г.'");
        Calendar cal = Calendar.getInstance();

        while (true) {
            try {
                System.out.println("\n=====================================\nПоочередно введите дату(или 0 для выхода из задания):");

                int year;
                while (true) {
                    System.out.print("Год >>> ");
                    year = scanner.nextInt();
                    if (year == 0){
                        return;
                    } else if (year > 9999) {
                        System.out.println("Год не может быть больше 9999");
                    } else if (year < 1) {
                            System.out.println("Год не может быть меньше 1");
                    } else {
                        break;
                    }
                }

                int month;
                while (true) {
                    System.out.print("Месяц >>> ");
                    month = scanner.nextInt() - 1;
                    if(month == -1){
                        return;
                    }else if (month < 0) {
                        System.out.println("Месяц не может быть меньше 1");
                    } else if (month > 11) {
                        System.out.println("Месяц не может быть больше 12");
                    } else {
                        break;
                    }
                }

                int day;
                while (true) {
                    System.out.print("День >>> ");
                    day = scanner.nextInt();
                    if(day == 0){
                        return;
                    } else if (day < 1) {
                        System.out.println("День не может быть меньше 1");
                        continue;
                    }

                    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                    if (month == 1) {
                        if (!isLeapYear && day > 28) {
                            System.out.println("Год не високосный, в феврале максимум 28 дней.");
                            continue;
                        } else if (isLeapYear && day > 29) {
                            System.out.println("Год високосный, в феврале максимум 29 дней.");
                            continue;
                        }
                    }

                    if ((month == 0 || month == 2 || month == 4 || month == 6 ||
                            month == 7 || month == 9 || month == 11) && day > 31) {
                        System.out.println("В данном месяце не может быть больше 31 дня.");
                        continue;
                    }

                    if ((month == 3 || month == 5 || month == 8 || month == 10) && day > 30) {
                        System.out.println("В данном месяце не может быть больше 30 дней.");
                        continue;
                    }

                    break;
                }

                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);

                Date date = cal.getTime();
                System.out.println(sdf.format(date));

            }catch(InputMismatchException e){
                scanner.nextLine();
            }
        }
    }
}