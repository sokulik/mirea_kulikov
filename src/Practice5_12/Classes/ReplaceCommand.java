package Practice5_12.Classes;

import Practice5_12.Interfaces.Command;
import java.lang.StringBuilder;

public class ReplaceCommand implements Command {
    private StringBuilder stringBuilder;
    private int start;
    private int end;
    private String original;
    private String replacement;

    public ReplaceCommand(StringBuilder stringBuilder, int start, int end, String str) {
        this.stringBuilder = stringBuilder;
        this.start = start;
        this.end = end;
        this.original = stringBuilder.substring(start, end);
        this.replacement = str;
    }

    @Override
    public void execute() {
        stringBuilder.replace(start, end, replacement);
    }

    @Override
    public void undo() {
        stringBuilder.replace(start, start + replacement.length(), original);
    }

    @Override
    public String getDescription() {
        return "Replace(" + start + "-" + end + "): '" + original + "' → '" + replacement + "'";
    }
}