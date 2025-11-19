package Practices.Practice5.Classes.T13;

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