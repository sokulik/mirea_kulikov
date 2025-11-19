package Practices.Practice5.Classes.T13;

import java.util.ArrayList;
import java.util.List;

public class ObservableStringBuilderImpl implements ObservableStringBuilder {
    private StringBuilder stringBuilder;
    private List<StringBuilderObserver> observers;

    public ObservableStringBuilderImpl() {
        this.stringBuilder = new StringBuilder();
        this.observers = new ArrayList<>();
    }

    public ObservableStringBuilderImpl(String str) {
        this.stringBuilder = new StringBuilder(str);
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(StringBuilderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(StringBuilderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String operation) {
        for (StringBuilderObserver observer : observers) {
            observer.update(stringBuilder.toString(), operation);
        }
    }

    @Override
    public ObservableStringBuilder append(String str) {
        stringBuilder.append(str);
        notifyObservers("append('" + str + "')");
        return this;
    }

    @Override
    public ObservableStringBuilder delete(int start, int end) {
        // Проверка границ перед удалением
        if (start < 0 || end > stringBuilder.length() || start > end) {
            notifyObservers("delete(" + start + ", " + end + ") -> ERROR: invalid range");
            return this;
        }

        String deleted = stringBuilder.substring(start, end);
        stringBuilder.delete(start, end);
        notifyObservers("delete(" + start + ", " + end + ") -> removed: '" + deleted + "'");
        return this;
    }

    @Override
    public ObservableStringBuilder insert(int offset, String str) {
        // Проверка границ перед вставкой
        if (offset < 0 || offset > stringBuilder.length()) {
            notifyObservers("insert(" + offset + ", '" + str + "') -> ERROR: invalid position");
            return this;
        }

        stringBuilder.insert(offset, str);
        notifyObservers("insert(" + offset + ", '" + str + "')");
        return this;
    }

    @Override
    public ObservableStringBuilder replace(int start, int end, String str) {
        // Проверка границ перед заменой
        if (start < 0 || end > stringBuilder.length() || start > end) {
            notifyObservers("replace(" + start + ", " + end + ", '" + str + "') -> ERROR: invalid range");
            return this;
        }

        String replaced = stringBuilder.substring(start, end);
        stringBuilder.replace(start, end, str);
        notifyObservers("replace(" + start + ", " + end + ", '" + str + "') -> replaced: '" + replaced + "'");
        return this;
    }

    @Override
    public ObservableStringBuilder reverse() {
        stringBuilder.reverse();
        notifyObservers("reverse()");
        return this;
    }

    @Override
    public int length() {
        return stringBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    @Override
    public String substring(int start) {
        return stringBuilder.substring(start);
    }

    @Override
    public String substring(int start, int end) {
        return stringBuilder.substring(start, end);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}