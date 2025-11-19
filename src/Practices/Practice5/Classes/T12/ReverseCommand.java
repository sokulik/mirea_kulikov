package Practices.Practice5.Classes.T12;

import java.lang.StringBuilder;

public class ReverseCommand implements Command {
    private StringBuilder stringBuilder;
    private String original;

    public ReverseCommand(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        this.original = stringBuilder.toString();
    }

    @Override
    public void execute() {
        stringBuilder.reverse();
    }

    @Override
    public void undo() {
        stringBuilder.replace(0, stringBuilder.length(), original);
    }

    @Override
    public String getDescription() {
        return "Reverse: '" + original + "'";
    }
}