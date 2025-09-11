package ru.mirea.kulikov.pr1;

import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println("\nМеню выбора:");
        System.out.println("1 - Задание 1");
        System.out.println("2 - Задание 2");
        System.out.println("3 - Задание 3");
        System.out.println("4 - Задание 4");
        System.out.println("5 - Задание 5");
        System.out.println("0 - Выход");
        System.out.println("Выберите задачу:");

        choice = scanner.nextInt();

        switch (choice){
            case 1:
                zadanie1();
                break;
            case 2:
                zadanie2();
                break;
            case 3:
                zadanie3(args);
                break;
            case 4:
                zadanie4(args);
                break;
            case 5:
                zadanie5();
                break;
            case 0:
                System.out.println("Выход(");
                break;
            default:
                System.out.println("Вы ввели недопустимое значение. Поробуйте еще раз.");
        }
    }
    while (choice != 0);
    scanner.close();
    }

    public static void zadanie1(){
        System.out.println("Задание 1");
        int[] massiv = {5, 1, 9, 13, 4, 25, 37};
        int summa = 0;

        for (int i = 0; i < massiv.length; i++) {
            summa = massiv[i];
        }
        double avg = (double) summa / massiv.length;

        System.out.println("Сумма" + summa);
        System.out.println("Среднее арифметическое" + avg);
    }

    public static void zadanie2(){
        System.out.println("Задание 2");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива");
        int size = scanner.nextInt();
        int[] massive = new int[size];
        int i = 0;

        do {
            System.out.println("Введите элемент " + (i+1));
            massive[i] = scanner.nextInt();
            i++;
        } while (i < size);

        int summa = 0;
        int min = massive[0];
        int max = massive[0];
        i = 0;

        while (i < size) {
            summa += massive[i];
            if (massive[i] < min) min = massive[i];
            if (massive[i] > max) max = massive[i];
            i++;
        }
        System.out.println("Массив: " + Arrays.toString(massive));
        System.out.println("Сумма: " + summa);
        System.out.println("Минимальное значение: " + min);
        System.out.println("Максимальное значение: " + max);
    }

    public static void zadanie3(String[] args){
        System.out.println("Задание 3");
        System.out.println("Аргмуенты командной строки: ");
        if (args.length == 0){
            System.out.println("Аргументов нет");
            return;
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println("Аргумент " + (i+1) + ":" + args[i]);
        }
    }

    public static void zadanie4(String[] args){
        System.out.println("Задание 4");
        System.out.println("Первые 10 чисел гармонического ряда: ");
        for (int i = 1; i <= 100; i++) {
            double chislo = 1.0 / i;
            System.out.printf("1/%d = %.5f%n", i, chislo);
        }

    }

    public static void zadanie5() {
        System.out.println("Задание 5");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число для вычисления его факториала");
        int chislo = scanner.nextInt();
        long fact = Factorial(chislo);
        System.out.printf("Факториал %d! = %d", chislo, fact);
    }
        public static long Factorial(int n){
        long factorial = 1;
        for (int i = 2; i <= n; i++){
            factorial *= i;
        }
        return factorial;

    }
}