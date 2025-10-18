package Practice5_13.Classes;

import Practice5_13.Interfaces.StringBuilderObserver;
import java.util.ArrayList;
import java.util.List;

public class ChangeTrackerObserver implements StringBuilderObserver {
    private List<String> history = new ArrayList<>();

    @Override
    public void update(String newState, String operation) {
        history.add("Операция: " + operation + " -> '" + newState + "'");
        System.out.println("[Трекер изменений] Всего изменений: " + history.size());

        // Показываем последние 3 изменения
        int start = Math.max(0, history.size() - 3);
        for (int i = start; i < history.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + history.get(i));
        }
    }
}