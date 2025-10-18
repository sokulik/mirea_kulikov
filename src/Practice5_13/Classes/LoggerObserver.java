package Practice5_13.Classes;

import Practice5_13.Interfaces.StringBuilderObserver;

public class LoggerObserver implements StringBuilderObserver {
    private String name;

    public LoggerObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String newState, String operation) {
        System.out.println("[" + name + "] Операция: " + operation + ", Результат: '" + newState + "'");
    }
}