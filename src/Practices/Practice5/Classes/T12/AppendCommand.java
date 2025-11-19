package Practices.Practice5.Classes.T12;

import java.lang.StringBuilder;

public class AppendCommand implements Command {
    private StringBuilder stringBuilder;
    private String appended;

    public AppendCommand(StringBuilder stringBuilder, String str) {
        this.stringBuilder = stringBuilder;
        this.appended = str;
    }

    @Override
    public void execute() {
        stringBuilder.append(appended);
    }

    @Override
    public void undo() {
        stringBuilder.delete(stringBuilder.length() - appended.length(), stringBuilder.length());
    }


    public String getDescription() {
        return "Append: '" + appended + "'";
    }
}