package Practices.Practice5.Classes.T12;

import java.lang.StringBuilder;

public class DeleteCommand implements Command {
    private StringBuilder stringBuilder;
    private int start;
    private int end;
    private String deleted;

    public DeleteCommand(StringBuilder stringBuilder, int start, int end) {
        this.stringBuilder = stringBuilder;
        this.start = start;
        this.end = end;
        this.deleted = stringBuilder.substring(start, end);
    }

    @Override
    public void execute() {
        stringBuilder.delete(start, end);
    }

    @Override
    public void undo() {
        stringBuilder.insert(start, deleted);
    }

    @Override
    public String getDescription() {
        return "Delete(" + start + "-" + end + "): '" + deleted + "'";
    }
}