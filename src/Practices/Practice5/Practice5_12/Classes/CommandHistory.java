package Practice5_12.Classes;

import Practice5_12.Interfaces.Command;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CommandHistory {
    private Stack<Command> history;
    private Stack<Command> redoStack;

    public CommandHistory() {
        this.history = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void push(Command command) {
        history.push(command);
        redoStack.clear();
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    public Command undo() {
        if (!canUndo()) return null;
        Command command = history.pop();
        redoStack.push(command);
        return command;
    }

    public Command redo() {
        if (!canRedo()) return null;
        Command command = redoStack.pop();
        history.push(command);
        return command;
    }

    public void clear() {
        history.clear();
        redoStack.clear();
    }

    public List<String> getHistory() {
        List<String> historyList = new ArrayList<>();
        for (int i = 0; i < history.size(); i++) {
            historyList.add((i + 1) + ". " + history.get(i).getDescription());
        }
        return historyList;
    }
}