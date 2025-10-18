package Practice5_13;

import Practice5_13.Classes.*;
import Practice5_13.Interfaces.ObservableStringBuilder;
import Practice5_13.Interfaces.StringBuilderObserver;

public class Task13 {
    public static void task13() {
        System.out.println("=== ObservableStringBuilder Демо ===");

        ObservableStringBuilder osb = new ObservableStringBuilderImpl();

        // Исправлены опечатки в названиях
        StringBuilderObserver logger = new LoggerObserver("Логгер");
        StringBuilderObserver stats = new StatisticsObserver();
        StringBuilderObserver tracker = new ChangeTrackerObserver();

        osb.addObserver(logger);
        osb.addObserver(stats);
        osb.addObserver(tracker);

        System.out.println("\n--- Выполняем операции ---");
        osb.append("Hello");
        osb.append(" World");
        osb.insert(5, ",");
        osb.replace(7, 12, "Java"); // Заменяем "World" на "Java"

        // Исправленная операция delete - правильные границы
        System.out.println("\n--- Пытаемся удалить символ ---");
        osb.delete(11, 12); // Удаляем последний символ если он есть

        System.out.println("\n--- Удаляем статистического наблюдателя ---");
        osb.removeObserver(stats);

        osb.append("!");
        osb.reverse();

        // Тестируем обработку ошибок
        System.out.println("\n--- Тестируем обработку ошибок ---");
        osb.delete(100, 200); // Неверные границы
        osb.insert(100, "test"); // Неверная позиция

        System.out.println("\n--- Финальный результат ---");
        System.out.println("Строка: " + osb.toString());
        System.out.println("Длина: " + osb.length());
    }
}