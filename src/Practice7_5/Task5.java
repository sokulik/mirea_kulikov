package Practice7_5;

import java.util.*;

public class Task5 {
    private static final int COUNT = 10000000;

    public static void task5() {
        System.out.println("Сравнение ArrayList vs LinkedList");
        System.out.println("Количество элементов: " + COUNT);
        System.out.println("Выполнение команды........");

        List<Integer> arrayList = new ArrayList<>();
        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < 100; i++) {
            arrayList.addFirst(i);
        }

        for (int i = 0; i < 100; i++) {
            arrayList.removeFirst();
        }

        long arrayListTime = System.currentTimeMillis() - start;

        List<Integer> linkedList = new LinkedList<>();
        start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < 100; i++) {
            linkedList.addFirst(i);
        }

        for (int i = 0; i < 100; i++) {
            linkedList.removeFirst();
        }

        long linkedListTime = System.currentTimeMillis() - start;

        System.out.println("\nВыполнено!"+"\nРезультаты:");
        System.out.printf("ArrayList:  %d мс\n", arrayListTime);
        System.out.printf("LinkedList: %d мс\n", linkedListTime);
        System.out.println();

        if (arrayListTime < linkedListTime) {
            System.out.printf("ArrayList быстрее на %.2f%%\n",
                    (linkedListTime - arrayListTime) * 100.0 / linkedListTime);
        } else {
            System.out.printf("LinkedList быстрее на %.2f%%\n",
                    (arrayListTime - linkedListTime) * 100.0 / arrayListTime);
        }
    }
}