package Practice5_13.Interfaces;

public interface ObservableStringBuilder {
    void addObserver(StringBuilderObserver observer);
    void removeObserver(StringBuilderObserver observer);
    void notifyObservers(String operation);

    // Основные методы StringBuilder
    ObservableStringBuilder append(String str);
    ObservableStringBuilder delete(int start, int end);
    ObservableStringBuilder insert(int offset, String str);
    ObservableStringBuilder replace(int start, int end, String str);
    ObservableStringBuilder reverse();

    int length();
    char charAt(int index);
    String substring(int start);
    String substring(int start, int end);
    String toString();
}