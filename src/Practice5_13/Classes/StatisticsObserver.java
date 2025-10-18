package Practice5_13.Classes;

import Practice5_13.Interfaces.StringBuilderObserver;

public class StatisticsObserver implements StringBuilderObserver {
    private int operationsCount = 0;
    private int totalLength = 0;

    @Override
    public void update(String newState, String operation) {
        operationsCount++;
        totalLength += newState.length();
        double averageLength = (double) totalLength / operationsCount;
        System.out.println("[Статистика] Операций: " + operationsCount +
                ", Текущая длина: " + newState.length() +
                ", Средняя длина: " + String.format("%.1f", averageLength));
    }
}