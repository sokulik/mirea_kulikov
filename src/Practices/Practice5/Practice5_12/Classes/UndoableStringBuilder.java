package Practice5_12.Classes;

import Practice5_12.Interfaces.Command;
import java.lang.StringBuilder;

public class UndoableStringBuilder {
    private StringBuilder stringBuilder;
    private CommandHistory history;
    private CommandFactory commandFactory;

    public UndoableStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new CommandHistory();
        this.commandFactory = new CommandFactory(stringBuilder);
    }

    public UndoableStringBuilder(String str) {
        this.stringBuilder = new StringBuilder(str);
        this.history = new CommandHistory();
        this.commandFactory = new CommandFactory(stringBuilder);
    }

    public UndoableStringBuilder append(String str) {
        Command command = commandFactory.createAppendCommand(str);
        command.execute();
        history.push(command);
        return this;
    }

    public UndoableStringBuilder delete(int start, int end) {
        Command command = commandFactory.createDeleteCommand(start, end);
        command.execute();
        history.push(command);
        return this;
    }

    public UndoableStringBuilder insert(int offset, String str) {
        Command command = commandFactory.createInsertCommand(offset, str);
        command.execute();
        history.push(command);
        return this;
    }

    public UndoableStringBuilder replace(int start, int end, String str) {
        Command command = commandFactory.createReplaceCommand(start, end, str);
        command.execute();
        history.push(command);
        return this;
    }

    public UndoableStringBuilder reverse() {
        Command command = commandFactory.createReverseCommand();
        command.execute();
        history.push(command);
        return this;
    }

    public boolean undo() {
        Command command = history.undo();
        if (command != null) {
            command.undo();
            return true;
        }
        return false;
    }

    public boolean redo() {
        Command command = history.redo();
        if (command != null) {
            command.execute();
            return true;
        }
        return false;
    }

    public boolean canUndo() {
        return history.canUndo();
    }

    public boolean canRedo() {
        return history.canRedo();
    }

    public void clearHistory() {
        history.clear();
    }

    public java.util.List<String> getHistory() {
        return history.getHistory();
    }

    public int length() {
        return stringBuilder.length();
    }

    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    public String substring(int start) {
        return stringBuilder.substring(start);
    }

    public String substring(int start, int end) {
        return stringBuilder.substring(start, end);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}