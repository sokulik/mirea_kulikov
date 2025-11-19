package Practices.Practice5.Classes.T12;

import java.lang.StringBuilder;

public class InsertCommand implements Command {
    private StringBuilder stringBuilder;
    private int offset;
    private String inserted;

    public InsertCommand(StringBuilder stringBuilder, int offset, String str) {
        this.stringBuilder = stringBuilder;
        this.offset = offset;
        this.inserted = str;
    }

    @Override
    public void execute() {
        stringBuilder.insert(offset, inserted);
    }

    @Override
    public void undo() {
        stringBuilder.delete(offset, offset + inserted.length());
    }

    @Override
    public String getDescription() {
        return "Insert at " + offset + ": '" + inserted + "'";
    }
}